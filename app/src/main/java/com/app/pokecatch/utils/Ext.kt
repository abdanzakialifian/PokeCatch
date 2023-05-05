package com.app.pokecatch.utils

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {
    val searchQuery = MutableStateFlow("")
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(newText: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            searchQuery.value = newText ?: ""
            return true
        }
    })
    return searchQuery
}