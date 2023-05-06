package com.app.pokecatch.presentation.stats.view

import android.os.Build
import android.os.Bundle
import com.app.core.domain.model.StatsItem
import com.app.pokecatch.databinding.FragmentStatsBinding
import com.app.pokecatch.presentation.stats.adapter.StatsAdapter
import com.app.pokecatch.presentation.base.BaseVBFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StatsFragment : BaseVBFragment<FragmentStatsBinding>() {

    @Inject
    lateinit var adapter: StatsAdapter
    override fun getViewBinding(): FragmentStatsBinding =
        FragmentStatsBinding.inflate(layoutInflater)

    override fun initView() {
        setStats()
    }

    private fun setStats() {
        if (arguments != null) {
            val statsList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelableArrayList(STATS_LIST, StatsItem::class.java)
            } else {
                arguments?.getParcelableArrayList(STATS_LIST)
            }
            adapter.submitList(statsList)
            binding?.rvStats?.adapter = adapter
        }
    }

    companion object {
        private const val STATS_LIST = "STATS LIST"
        fun newInstance(statsList: ArrayList<StatsItem>): StatsFragment {
            val fragment = StatsFragment()
            Bundle().apply {
                putParcelableArrayList(STATS_LIST, statsList)
                fragment.arguments = this
            }
            return fragment
        }
    }
}