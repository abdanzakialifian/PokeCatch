package com.app.pokecatch.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.core.domain.model.TypesItem
import com.app.pokecatch.databinding.ItemListTypeBinding
import com.app.pokecatch.utils.capitalizeWords
import javax.inject.Inject

class TypeAdapter @Inject constructor() : ListAdapter<TypesItem, TypeAdapter.TypeViewHolder>(
    DIFF_CALLBACK
) {
    inner class TypeViewHolder(private val binding: ItemListTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TypesItem) {
            binding.tvType.text = item.type?.name?.capitalizeWords()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder =
        ItemListTypeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).run {
            TypeViewHolder(this)
        }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TypesItem>() {
            override fun areItemsTheSame(oldItem: TypesItem, newItem: TypesItem): Boolean =
                oldItem.type?.name == newItem.type?.name

            override fun areContentsTheSame(oldItem: TypesItem, newItem: TypesItem): Boolean =
                oldItem == newItem
        }
    }
}