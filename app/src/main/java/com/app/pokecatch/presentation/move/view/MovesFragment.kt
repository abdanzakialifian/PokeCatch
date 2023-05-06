package com.app.pokecatch.presentation.move.view

import android.os.Build
import android.os.Bundle
import com.app.core.domain.model.MovesItem
import com.app.pokecatch.databinding.FragmentMovesBinding
import com.app.pokecatch.presentation.base.BaseVBFragment
import com.app.pokecatch.presentation.move.adapter.MoveAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovesFragment : BaseVBFragment<FragmentMovesBinding>() {

    @Inject
    lateinit var adapter: MoveAdapter

    override fun getViewBinding(): FragmentMovesBinding =
        FragmentMovesBinding.inflate(layoutInflater)

    override fun initView() {
        setMoves()
    }

    private fun setMoves() {
        if (arguments != null) {
            val movesList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelableArrayList(MOVES_LIST, MovesItem::class.java)
            } else {
                arguments?.getParcelableArrayList(MOVES_LIST)
            }
            adapter.submitList(movesList)
            binding?.rvMove?.adapter = adapter
        }
    }

    companion object {
        private const val MOVES_LIST = "MOVES LIST"
        fun newInstance(movesList: ArrayList<MovesItem>): MovesFragment {
            val fragment = MovesFragment()
            Bundle().apply {
                putParcelableArrayList(MOVES_LIST, movesList)
                fragment.arguments = this
            }
            return fragment
        }
    }
}