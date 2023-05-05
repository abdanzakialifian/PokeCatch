package com.app.pokecatch.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.pokecatch.databinding.ItemLoadingBinding

class LoadingStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadingStateAdapter.LoadStateViewHolder>() {
    inner class LoadStateViewHolder(private val binding: ItemLoadingBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener { retry.invoke() }
        }
            fun bind(loadState: LoadState) {
                binding.apply {
                    progressBar.isVisible = loadState is LoadState.Loading
                    btnRetry.isVisible = loadState is LoadState.Error
                }
            }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
        ItemLoadingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).run {
            LoadStateViewHolder(this, retry)
        }
}