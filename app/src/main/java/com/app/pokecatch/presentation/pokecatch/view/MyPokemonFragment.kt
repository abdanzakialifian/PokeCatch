package com.app.pokecatch.presentation.pokecatch.view

import com.app.pokecatch.databinding.FragmentMyPokemonBinding
import com.app.pokecatch.presentation.base.BaseVBFragment

class MyPokemonFragment : BaseVBFragment<FragmentMyPokemonBinding>() {

    override fun getViewBinding(): FragmentMyPokemonBinding =
        FragmentMyPokemonBinding.inflate(layoutInflater)

    override fun initView() {}
}