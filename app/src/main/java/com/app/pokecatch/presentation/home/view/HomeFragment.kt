package com.app.pokecatch.presentation.home.view

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.app.pokecatch.databinding.FragmentHomeBinding
import com.app.pokecatch.presentation.base.BaseVBFragment
import com.app.pokecatch.presentation.home.adapter.HomeAdapter
import com.app.pokecatch.presentation.home.adapter.LoadingStateAdapter
import com.app.pokecatch.presentation.home.viewmodel.HomeViewModel
import com.app.pokecatch.utils.gone
import com.app.pokecatch.utils.visible
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
        viewLifecycleOwner.lifecycleScope.launch {
            binding?.searchView?.let {
                viewModel.searchFlow(it)
                    .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                    .collect {query ->
                        viewModel.setQuery(query)
                    }
            }
        }
    }

    private fun getPokemonList() {
        val footerAdapter = LoadingStateAdapter {
            adapter.retry()
        }

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        // set loading footer in center
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == adapter.itemCount && footerAdapter.itemCount > 0) 2 else 1
            }

        }

        binding?.apply {
            rvPokemon.adapter = adapter.withLoadStateFooter(
                footer = footerAdapter
            )
            rvPokemon.layoutManager = gridLayoutManager
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPokemonList.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect { pagingData ->
                adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
                adapter.addLoadStateListener { loadState ->
                    when (loadState.refresh) {
                        is LoadState.NotLoading -> {
                            binding?.apply {
                                shimmerLoading.stopShimmer()
                                shimmerLoading.gone()
                                rvPokemon.visible()
                            }
                        }

                        is LoadState.Loading -> {
                            binding?.apply {
                                shimmerLoading.startShimmer()
                                shimmerLoading.visible()
                                rvPokemon.gone()
                            }
                        }

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