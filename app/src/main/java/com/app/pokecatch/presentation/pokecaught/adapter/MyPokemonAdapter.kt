package com.app.pokecatch.presentation.pokecaught.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.core.domain.model.Pokemon
import com.app.core.domain.model.TypesItem
import com.app.pokecatch.databinding.ItemListCaughtBinding
import com.app.pokecatch.presentation.detail.adapter.TypeAdapter
import com.app.pokecatch.utils.setOnSingleClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.google.gson.Gson
import javax.inject.Inject

class MyPokemonAdapter @Inject constructor() :
    ListAdapter<Pokemon, MyPokemonAdapter.MyPokemonViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MyPokemonViewHolder(private val binding: ItemListCaughtBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pokemon) {
            binding.apply {
                val adapter = TypeAdapter()
                val listTypes =
                    Gson().fromJson(item.pokemonType, Array<TypesItem>::class.java).toList()
                adapter.submitList(listTypes)
                rvType.adapter = adapter
                tvPokemonName.text = item.pokemonName
                imgPokemon.transitionName = item.pokemonImage
                tvPokemonName.transitionName = item.pokemonName
                setImagePokemon(
                    itemView.context, itemView, binding.imgPokemon, binding.tvPokemonName, item
                )
                setBackgroundCardView(itemView.context, this, item.pokemonImage ?: "")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPokemonViewHolder =
        ItemListCaughtBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).run {
            MyPokemonViewHolder(this)
        }

    override fun onBindViewHolder(holder: MyPokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun setBackgroundCardView(
        context: Context, binding: ItemListCaughtBinding, imageUrl: String
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
                        }
                    }
                }
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    }

    private fun setImagePokemon(
        context: Context,
        itemView: View,
        pokemonImage: AppCompatImageView,
        pokemonName: TextView,
        item: Pokemon
    ) {
        Glide.with(context).load(item.pokemonImage).listener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable?>?,
                isFirstResource: Boolean
            ): Boolean = false

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable?>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                // item click if load image success
                itemView.setOnSingleClickListener {
                    onItemClickCallback.onItemClicked(
                        item.pokemonName ?: "",
                        item.pokemonImage ?: "",
                        pokemonImage,
                        pokemonName
                    )
                }
                return false
            }
        }).into(pokemonImage)
    }

    interface OnItemClickCallback {
        fun onItemClicked(
            name: String, imageUrl: String, imageView: AppCompatImageView, textView: TextView
        )
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.pokemonId == newItem.pokemonId

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }
}