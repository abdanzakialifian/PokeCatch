package com.app.pokecatch.presentation.splash

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.app.pokecatch.databinding.FragmentSplashScreenBinding
import com.app.pokecatch.presentation.base.BaseVBFragment

class SplashScreenFragment : BaseVBFragment<FragmentSplashScreenBinding>() {

    override fun getViewBinding(): FragmentSplashScreenBinding =
        FragmentSplashScreenBinding.inflate(layoutInflater)

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeFragment())
        }, DELAY_SPLASH_SCREEN)
    }

    companion object {
        private const val DELAY_SPLASH_SCREEN = 3000L
    }
}