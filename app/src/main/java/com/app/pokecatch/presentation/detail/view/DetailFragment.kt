package com.app.pokecatch.presentation.detail.view

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.app.core.domain.model.MovesItem
import com.app.core.domain.model.StatsItem
import com.app.core.utils.UiState
import com.app.pokecatch.R
import com.app.pokecatch.databinding.FragmentDetailBinding
import com.app.pokecatch.presentation.base.BaseVBFragment
import com.app.pokecatch.presentation.detail.adapter.TypeAdapter
import com.app.pokecatch.presentation.detail.adapter.ViewPagerAdapter
import com.app.pokecatch.presentation.detail.viewmodel.DetailViewModel
import com.app.pokecatch.presentation.move.view.MovesFragment
import com.app.pokecatch.presentation.stats.view.StatsFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseVBFragment<FragmentDetailBinding>() {

    @Inject
    lateinit var adapter: TypeAdapter
    private val viewModel by viewModels<DetailViewModel>()
    private val navArgs by navArgs<DetailFragmentArgs>()
    private var statsList: List<StatsItem>? = null
    private var movesList: List<MovesItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun getViewBinding(): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override fun initView() {
        setViewPager()
        getPokemon()
        binding?.apply {
            tvPokemonName.apply {
                transitionName = navArgs.name
                text = navArgs.name
            }
            imgPokemon.apply {
                Glide.with(requireContext())
                    .load(navArgs.imageUrl)
                    .into(this)
                setBackgroundCardView(navArgs.imageUrl ?: "")
                transitionName = navArgs.imageUrl
            }
        }
        binding?.imgBack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setBackgroundCardView(imageUrl: String) {
        Glide.with(requireContext())
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    Palette.from(resource).generate { palette ->
                        if (palette?.lightVibrantSwatch != null) {
                            binding?.apply {
                                cvPokemon.setCardBackgroundColor(
                                    palette.lightVibrantSwatch?.rgb ?: 0
                                )
                                btnCatchPokemon.backgroundTintList =
                                    ColorStateList.valueOf(palette.lightVibrantSwatch?.rgb ?: 0)
                            }
                        }
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }

    private fun getPokemon() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPokemon(navArgs.name ?: "")
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {}
                        is UiState.Success -> {
                            adapter.submitList(uiState.data.types)
                            binding?.rvType?.adapter = adapter
                            statsList = uiState.data.stats
                            movesList = uiState.data.moves
                            setViewPager()
                        }

                        is UiState.Error -> {}
                    }
                }
        }
    }

    private fun setViewPager() {
        val viewPager = binding?.viewPager
        val tabLayout = binding?.tabLayout
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
        viewPagerAdapter.addFragment(StatsFragment.newInstance(ArrayList(statsList ?: emptyList())))
        viewPagerAdapter.addFragment(MovesFragment.newInstance(ArrayList(movesList ?: emptyList())))
        viewPager?.adapter = viewPagerAdapter
        if (tabLayout != null && viewPager != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.base_stats,
            R.string.moves
        )
    }
}