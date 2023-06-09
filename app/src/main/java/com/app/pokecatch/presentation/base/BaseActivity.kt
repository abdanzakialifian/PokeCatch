package com.app.pokecatch.presentation.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.pokecatch.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}