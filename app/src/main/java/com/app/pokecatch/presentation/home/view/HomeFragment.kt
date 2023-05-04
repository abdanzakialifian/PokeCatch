package com.app.pokecatch.presentation.home.view

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.app.pokecatch.databinding.FragmentHomeBinding
import com.app.pokecatch.presentation.base.BaseVBFragment
import com.app.pokecatch.presentation.home.adapter.HomeAdapter
import com.app.pokecatch.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseVBFragment<FragmentHomeBinding>() {

    @Inject
    lateinit var adapter: HomeAdapter
    private val viewModel by viewModels<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun initView() {
        getPokemonList()
    }

    private fun getPokemonList() {
        binding?.rvPokemon?.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPokemonList.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect { pagingData ->
                adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
                adapter.addLoadStateListener { loadState ->
                    when (loadState.refresh) {
                        is LoadState.NotLoading -> {}
                        is LoadState.Loading -> {}
                        is LoadState.Error -> {}
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        binding?.rvPokemon?.adapter = null
        super.onDestroyView()
    }
}