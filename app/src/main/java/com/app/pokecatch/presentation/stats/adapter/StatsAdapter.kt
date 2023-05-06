package com.app.pokecatch.presentation.stats.adapter

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.core.domain.model.StatsItem
import com.app.pokecatch.R
import com.app.pokecatch.databinding.ItemListStatsBinding
import javax.inject.Inject

class StatsAdapter @Inject constructor() : ListAdapter<StatsItem, StatsAdapter.StatsViewHolder>(
    DIFF_CALLBACK
) {
    inner class StatsViewHolder(private val binding: ItemListStatsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StatsItem) {
            binding.apply {
                binding.tvBaseStats.text = item.baseStat.toString()
                setCustomStats(
                    itemView.context,
                    binding.progressBar,
                    binding.tvStats,
                    item.stat?.name ?: ""
                )
                progressAnimation(binding.progressBar, item.baseStat ?: 0)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder =
        ItemListStatsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).run {
            StatsViewHolder(this)
        }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun setCustomStats(
        context: Context,
        progressBar: ProgressBar,
        textView: TextView,
        stats: String
    ) {
        when (stats) {
            "hp" -> {
                progressBar.progressTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
                textView.text = context.resources.getString(R.string.hp)
            }

            "attack" -> {
                progressBar.progressTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        R.color.orange
                    )
                )
                textView.text = context.resources.getString(R.string.attack)
            }

            "defense" -> {
                progressBar.progressTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        R.color.blue
                    )
                )
                textView.text = context.resources.getString(R.string.defense)
            }

            "special-attack" -> {
                progressBar.progressTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        R.color.yellow
                    )
                )
                textView.text = context.resources.getString(R.string.special_attack)
            }

            "special-defense" -> {
                progressBar.progressTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        R.color.dark_green
                    )
                )
                textView.text = context.resources.getString(R.string.special_defense)
            }

            "speed" -> {
                progressBar.progressTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        R.color.grey
                    )
                )
                textView.text = context.resources.getString(R.string.speed)
            }
        }
    }

    private fun progressAnimation(progressBar: ProgressBar, baseStats: Int) {
        val animation = ObjectAnimator.ofInt(progressBar, "progress", 0, baseStats)
        animation.duration = 3500L
        animation.interpolator = DecelerateInterpolator()
        animation.start()
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StatsItem>() {
            override fun areItemsTheSame(oldItem: StatsItem, newItem: StatsItem): Boolean =
                oldItem.stat?.name == newItem.stat?.name

            override fun areContentsTheSame(oldItem: StatsItem, newItem: StatsItem): Boolean =
                oldItem == newItem
        }
    }
}