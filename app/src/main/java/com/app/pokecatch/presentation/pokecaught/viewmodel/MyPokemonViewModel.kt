package com.app.pokecatch.presentation.pokecaught.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.domain.interfaces.PokemonUseCase
import com.app.core.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPokemonViewModel @Inject constructor(private val pokemonUseCase: PokemonUseCase) : ViewModel() {
    val getAllPokemon: Flow<List<Pokemon>> = pokemonUseCase.getAllPokemon().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun deletePokemon(pokemonId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonUseCase.deletePokemon(pokemonId)
        }
    }
}