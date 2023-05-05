package com.app.pokecatch.presentation.home.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagingDataAdapter
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.core.domain.model.PokemonResultsItem
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

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class HomeViewHolder(private val binding: ItemListPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonResultsItem) {
            binding.apply {
                Glide.with(itemView.context).load(item.url?.getImageUrl()).into(imgPokemon)
                tvPokemonName.text = item.name
                imgPokemon.transitionName = item.url?.getImageUrl()
                tvPokemonName.transitionName = item.name
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(
                        item.name ?: "",
                        item.url?.getImageUrl() ?: "",
                        imgPokemon,
                        tvPokemonName
                    )
                }
                setBackgroundCardView(itemView.context, this, item.url?.getImageUrl() ?: "")
            }
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

    private fun setBackgroundCardView(
        context: Context, binding: ItemListPokemonBinding, imageUrl: String
    ) {
        Glide.with(context).asBitmap().load(imageUrl).into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap, transition: Transition<in Bitmap>?
            ) {
                Palette.from(resource).generate { palette ->
                    if (palette?.lightVibrantSwatch != null) {
                        binding.apply {
                            cvPokemon.setCardBackgroundColor(
                                palette.lightVibrantSwatch?.rgb ?: 0
                            )
                            tvPokemonName.setTextColor(
                                palette.lightVibrantSwatch?.titleTextColor ?: 0
                            )
                            imgPokeball.setColorFilter(
                                palette.lightVibrantSwatch?.titleTextColor ?: 0,
                                PorterDuff.Mode.MULTIPLY
                            )
                        }
                    }
                }
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    }

    interface OnItemClickCallback {
        fun onItemClicked(
            name: String,
            imageUrl: String,
            imageView: AppCompatImageView,
            textView: TextView
        )
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PokemonResultsItem>() {
            override fun areItemsTheSame(
                oldItem: PokemonResultsItem, newItem: PokemonResultsItem
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: PokemonResultsItem, newItem: PokemonResultsItem
            ): Boolean = oldItem == newItem
        }
    }
}