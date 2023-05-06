package com.app.pokecatch.utils

import android.os.SystemClock
import android.view.View
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale

fun String.extractId(): Int = this.substringAfter("pokemon").replace("/", "").toInt()

fun String.extractName(): String {
    val split = this.split(" ")
    return split.last().replace("(", "").replace(")", "")
}

fun String.deleteLastName(): String {
    val split = this.split(" ")
    val stringBuilder = StringBuilder()
    split.forEachIndexed { index, name ->
        if (index != split.lastIndex) {
            if(index == split.lastIndex - 1) stringBuilder.append(name) else stringBuilder.append(name).append(" ")
        }
    }
    return stringBuilder.toString()
}

fun String.getLastName(): String {
    val split = this.split(" ")
    return split.last()
}

fun String.getImageUrl(): String {
    val id = this.extractId()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
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

fun String.capitalizeWords(): String = split(" ").joinToString(" ") {
    it.replaceFirstChar { char ->
        if (char.isLowerCase()) char.titlecase(
            Locale.getDefault()
        ) else char.toString()
    }
}

fun View.setOnSingleClickListener(onClick: () -> Unit) {
    var mLastClickTime = 0L
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return@setOnClickListener
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        onClick.invoke()
    }
}

fun Int.getPrimeNumber(): Boolean {
    var isPrimeNumber = true
    for (i in 2..this / 2) {
        // condition for non prime number
        if (this % i == 0) {
            isPrimeNumber = false
            break
        }
    }
    return isPrimeNumber
}