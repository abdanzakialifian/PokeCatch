package com.app.pokecatch.presentation.about.view

import com.app.pokecatch.databinding.FragmentAboutBinding
import com.app.pokecatch.presentation.base.BaseVBFragment

class AboutFragment : BaseVBFragment<FragmentAboutBinding>() {

    override fun getViewBinding(): FragmentAboutBinding = FragmentAboutBinding.inflate(layoutInflater)

    override fun initView() {}
}