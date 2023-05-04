package com.app.pokecatch.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun String.extractId(): Int = this.substringAfter("pokemon").replace("/", "").toInt()

fun String.getImageUrl(): String {
    val id = this.extractId()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
}

fun ImageView.setImageUrl(pictUrl: String) {
    Glide.with(context)
        .load(pictUrl)
        .into(this)
}