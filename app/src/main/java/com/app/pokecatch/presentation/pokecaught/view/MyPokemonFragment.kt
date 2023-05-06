package com.app.pokecatch.presentation.pokecaught.view

import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.core.domain.model.Pokemon
import com.app.pokecatch.R
import com.app.pokecatch.databinding.FragmentMyPokemonBinding
import com.app.pokecatch.presentation.base.BaseVBFragment
import com.app.pokecatch.presentation.bottomsheet.CustomBottomSheetDialog
import com.app.pokecatch.presentation.pokecaught.adapter.MyPokemonAdapter
import com.app.pokecatch.presentation.pokecaught.viewmodel.MyPokemonViewModel
import com.app.pokecatch.utils.deleteLastName
import com.app.pokecatch.utils.extractName
import com.app.pokecatch.utils.getLastName
import com.app.pokecatch.utils.getPrimeNumber
import com.app.pokecatch.utils.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyPokemonFragment : BaseVBFragment<FragmentMyPokemonBinding>() {

    @Inject
    lateinit var adapter: MyPokemonAdapter
    private val viewModel by viewModels<MyPokemonViewModel>()
    private var pokemonList: MutableList<Pokemon>? = null

    override fun getViewBinding(): FragmentMyPokemonBinding =
        FragmentMyPokemonBinding.inflate(layoutInflater)

    override fun initView() {
        getPokemonCaught()
        setAdapterItemClick()
        binding?.imgBack?.setOnSingleClickListener {
            findNavController().navigateUp()
        }
    }

    private fun getPokemonCaught() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAllPokemon.flowWithLifecycle(
                    viewLifecycleOwner.lifecycle,
                    Lifecycle.State.STARTED
                ).collect { pokemonList ->
                    adapter.submitList(pokemonList)
                    binding?.rvMyPokemon?.adapter = adapter
                    this@MyPokemonFragment.pokemonList = pokemonList.toMutableList()
                    swipeDelete()
                }
        }
    }

    private fun swipeDelete() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // get prime number from random number from 1 until 100
                val randomNumber = (1..100).random()
                val isPrimeNumber = randomNumber.getPrimeNumber()
                if (isPrimeNumber) {
                    Toast.makeText(
                        requireContext(),
                        resources.getString(R.string.pokemon_released),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.deletePokemon(
                        pokemonList?.get(viewHolder.bindingAdapterPosition)?.pokemonId ?: 0
                    )
                    pokemonList?.toMutableList()?.removeAt(viewHolder.bindingAdapterPosition)
                    adapter.notifyItemRemoved(viewHolder.bindingAdapterPosition)
                } else {
                    Toast.makeText(
                        requireContext(),
                        resources.getString(R.string.pokemon_cannot_released),
                        Toast.LENGTH_SHORT
                    ).show()
                    adapter.submitList(pokemonList)
                    binding?.rvMyPokemon?.adapter = adapter
                }
            }
        }).attachToRecyclerView(binding?.rvMyPokemon)
    }

    private fun setAdapterItemClick() {
        adapter.setOnItemClickCallback(object : MyPokemonAdapter.OnItemClickCallback {
            override fun onItemClicked(
                name: String, imageUrl: String, imageView: AppCompatImageView, textView: TextView
            ) {
                val extras = FragmentNavigatorExtras(
                    imageView to imageUrl, textView to name.extractName()
                )
                val navigateToDetailFragment =
                    MyPokemonFragmentDirections.actionMyPokemonFragmentToDetailFragment(
                        name = name.extractName(), imageUrl = imageUrl
                    )
                findNavController().navigate(navigateToDetailFragment, extras)
            }

            override fun onEditClicked(id: Int, name: String) {
                CoroutineScope(Dispatchers.IO).launch {
                    val totalUpdate = viewModel.getTotalUpdate(id)
                    customBottomSheetDialog(id, name, totalUpdate)
                }
            }
        })
    }

    private fun customBottomSheetDialog(pokemonId: Int, pokemonNames: String, totalUpdate: Int) {
        val modifyName = if (totalUpdate == 0) {
            pokemonNames.deleteLastName()
        } else {
            pokemonNames.deleteLastName().deleteLastName()
        }
        val customBottomSheetDialog = CustomBottomSheetDialog.newInstance(modifyName)
        customBottomSheetDialog.setOnButtonClickCallback(object :
            CustomBottomSheetDialog.OnButtonClickCallback {
            override fun onButtonClicked(pokemonName: String) {
                val lastName = pokemonNames.getLastName()
                CoroutineScope(Dispatchers.IO).launch {
                    val isUpdated = viewModel.updatePokemonName(
                        pokemonId,
                        StringBuilder().append(pokemonName).append(" ").append("-$totalUpdate")
                            .append(" ")
                            .append(lastName).toString(),
                        totalUpdate = totalUpdate + 1
                    )
                    if (isUpdated == 1) {
                        getPokemonCaught()
                    }
                }
            }
        })
        customBottomSheetDialog.isCancelable = false
        customBottomSheetDialog.show(childFragmentManager, customBottomSheetDialog.tag)
    }
}