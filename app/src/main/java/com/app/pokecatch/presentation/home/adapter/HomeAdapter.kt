package com.app.pokecatch.presentation.home.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.core.domain.model.PokemonResultsItem
import com.app.pokecatch.R
import com.app.pokecatch.databinding.ItemListPokemonBinding
import com.app.pokecatch.utils.getImageUrl
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import javax.inject.Inject

class HomeAdapter @Inject constructor() :
    PagingDataAdapter<PokemonResultsItem, HomeAdapter.HomeViewHolder>(
        DIFF_CALLBACK
    ) {
    inner class HomeViewHolder(private val binding: ItemListPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonResultsItem) {
            Glide.with(itemView.context)
                .load(item.url?.getImageUrl())
                .into(binding.imgPokemon)

            Glide.with(itemView.context)
                .asBitmap()
                .load(item.url?.getImageUrl())
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        Palette.from(resource).generate { palette ->
                            if (palette?.lightVibrantSwatch != null) {
                                binding.cvPokemon.setCardBackgroundColor(palette.lightVibrantSwatch?.rgb ?: 0)
                                binding.tvPokemonName.setTextColor(palette.lightVibrantSwatch?.titleTextColor ?: 0)
                            }
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
            binding.tvPokemonName.text = item.name
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let { result ->
            holder.bind(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        ItemListPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).run {
            HomeViewHolder(this)
        }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PokemonResultsItem>() {
            override fun areItemsTheSame(
                oldItem: PokemonResultsItem,
                newItem: PokemonResultsItem
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: PokemonResultsItem,
                newItem: PokemonResultsItem
            ): Boolean = oldItem == newItem
        }
    }
}