package com.app.pokecatch.presentation.move.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.core.domain.model.MovesItem
import com.app.pokecatch.databinding.ItemListMoveBinding
import com.app.pokecatch.utils.capitalizeWords
import javax.inject.Inject

class MoveAdapter @Inject constructor() : ListAdapter<MovesItem, MoveAdapter.MoveViewHolder>(
    DIFF_CALLBACK
) {
    inner class MoveViewHolder(private val binding: ItemListMoveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovesItem) {
            binding.tvMove.text = item.move?.name?.replace("-", " ")?.capitalizeWords()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder =
        ItemListMoveBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).run {
            MoveViewHolder(this)
        }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovesItem>() {
            override fun areItemsTheSame(oldItem: MovesItem, newItem: MovesItem): Boolean =
                oldItem.move?.name == newItem.move?.name

            override fun areContentsTheSame(oldItem: MovesItem, newItem: MovesItem): Boolean =
                oldItem == newItem
        }
    }
}