package com.app.pokecatch.presentation.pokecaught.view

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.app.pokecatch.databinding.FragmentMyPokemonBinding
import com.app.pokecatch.presentation.base.BaseVBFragment
import com.app.pokecatch.presentation.pokecaught.adapter.MyPokemonAdapter
import com.app.pokecatch.presentation.pokecaught.viewmodel.MyPokemonViewModel
import com.app.pokecatch.utils.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyPokemonFragment : BaseVBFragment<FragmentMyPokemonBinding>() {

    @Inject
    lateinit var adapter: MyPokemonAdapter
    private val viewModel by viewModels<MyPokemonViewModel>()

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
            viewModel.getAllPokemon
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { pokemonList ->
                    adapter.submitList(pokemonList)
                    binding?.rvMyPokemon?.adapter = adapter
                }
        }
    }

    private fun setAdapterItemClick() {
        adapter.setOnItemClickCallback(object : MyPokemonAdapter.OnItemClickCallback {
            override fun onItemClicked(
                name: String,
                imageUrl: String,
                imageView: AppCompatImageView,
                textView: TextView
            ) {
                val extras = FragmentNavigatorExtras(
                    imageView to imageUrl,
                    textView to name
                )
                val navigateToDetailFragment =
                    MyPokemonFragmentDirections.actionMyPokemonFragmentToDetailFragment(
                        name = name,
                        imageUrl = imageUrl
                    )
                findNavController().navigate(navigateToDetailFragment, extras)
            }
        })
    }
}