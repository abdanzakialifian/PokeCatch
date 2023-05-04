package com.app.pokecatch.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.core.domain.interfaces.PokemonUseCase
import com.app.core.domain.model.PokemonResultsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(pokemonUseCase: PokemonUseCase) : ViewModel() {
    val getPokemonList: Flow<PagingData<PokemonResultsItem>> =
        pokemonUseCase.getPokemonList().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = PagingData.empty()
        ).cachedIn(viewModelScope)
}