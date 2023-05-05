package com.app.core.domain.interfaces

import androidx.paging.PagingData
import com.app.core.domain.model.DetailPokemon
import com.app.core.domain.model.PokemonResultsItem
import com.app.core.utils.UiState
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(query: String): Flow<PagingData<PokemonResultsItem>>
    fun getPokemon(name: String): Flow<UiState<DetailPokemon>>
}