package com.app.pokecatch.presentation.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.pokecatch.databinding.LayoutModelBottomSheetBinding
import com.app.pokecatch.utils.setOnSingleClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CustomBottomSheetDialog : BottomSheetDialogFragment() {
    private var _binding: LayoutModelBottomSheetBinding? = null
    private val binding get() = _binding

    private lateinit var onButtonClickCallback: OnButtonClickCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LayoutModelBottomSheetBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            btnCatchPokemon.setOnSingleClickListener {
                if (edtName.text?.isNotEmpty() == true) {
                    onButtonClickCallback.onButtonClicked(binding?.edtName?.text.toString())
                    this@CustomBottomSheetDialog.dismiss()
                }
            }
            imgClose.setOnSingleClickListener {
                this@CustomBottomSheetDialog.dismiss()
            }
            if (arguments != null) {
                val pokemonName = arguments?.getString(POKEMON_NAME)
                edtName.setText(pokemonName)
            }
        }
    }

    fun setOnButtonClickCallback(onButtonClickCallback: OnButtonClickCallback) {
        this.onButtonClickCallback = onButtonClickCallback
    }

    interface OnButtonClickCallback {
        fun onButtonClicked(pokemonName: String)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val POKEMON_NAME = "POKEMON NAME"
        fun newInstance(pokemonName: String): CustomBottomSheetDialog {
            val fragment = CustomBottomSheetDialog()
            Bundle().apply {
                putString(POKEMON_NAME, pokemonName)
                fragment.arguments = this
            }
            return fragment
        }
    }
}