package com.app.pokecatch.presentation.moves.view

import com.app.pokecatch.databinding.FragmentMovesBinding
import com.app.pokecatch.presentation.base.BaseVBFragment

class MovesFragment : BaseVBFragment<FragmentMovesBinding>() {

    override fun getViewBinding(): FragmentMovesBinding =
        FragmentMovesBinding.inflate(layoutInflater)

    override fun initView() {}
}