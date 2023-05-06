package com.app.pokecatch.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.domain.interfaces.PokemonUseCase
import com.app.core.domain.model.DetailPokemon
import com.app.core.domain.model.Pokemon
import com.app.core.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val pokemonUseCase: PokemonUseCase) :
    ViewModel() {
    private val pokemonName = MutableStateFlow("")

    fun setName(name: String) {
        this.pokemonName.value = name
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val getPokemon: Flow<UiState<DetailPokemon>> =
        pokemonName.flatMapLatest { name ->
            pokemonUseCase.getPokemon(name).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = UiState.Loading
            )
        }

    fun insertPokemon(pokemon: Pokemon) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonUseCase.insertPokemon(pokemon)
        }
    }

    fun checkPokemon(pokemonId: Int): Boolean = pokemonUseCase.checkPokemon(pokemonId)

    fun deletePokemon(pokemonId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonUseCase.deletePokemon(pokemonId)
        }
    }
}