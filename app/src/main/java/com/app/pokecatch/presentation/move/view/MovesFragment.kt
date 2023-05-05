package com.app.pokecatch.presentation.move.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.app.core.utils.UiState
import com.app.pokecatch.databinding.FragmentMovesBinding
import com.app.pokecatch.presentation.base.BaseVBFragment
import com.app.pokecatch.presentation.detail.viewmodel.DetailViewModel
import com.app.pokecatch.presentation.move.adapter.MoveAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovesFragment : BaseVBFragment<FragmentMovesBinding>() {

    @Inject
    lateinit var adapter: MoveAdapter
    private val viewModel by viewModels<DetailViewModel>()

    override fun getViewBinding(): FragmentMovesBinding =
        FragmentMovesBinding.inflate(layoutInflater)

    override fun initView() {
        if (arguments != null) {
            val pokemonName = arguments?.getString(POKEMON_NAME, "")
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getPokemon(pokemonName ?: "")
                    .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                    .collect { uiState ->
                        when (uiState) {
                            is UiState.Loading -> {}
                            is UiState.Success -> {
                                adapter.submitList(uiState.data.moves)
                                binding?.rvMove?.adapter = adapter
                            }

                            is UiState.Error -> {}
                        }
                    }
            }
        }
    }

    companion object {
        private const val POKEMON_NAME = "POKEMON NAME"
        fun newInstance(name: String): MovesFragment {
            val fragment = MovesFragment()
            Bundle().apply {
                putString(POKEMON_NAME, name)
                fragment.arguments = this
            }
            return fragment
        }
    }
}