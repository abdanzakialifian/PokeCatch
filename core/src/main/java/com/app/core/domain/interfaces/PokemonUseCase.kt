package com.app.core.domain.interfaces

import androidx.paging.PagingData
import com.app.core.domain.model.PokemonResultsItem
import kotlinx.coroutines.flow.Flow

interface PokemonUseCase {
    fun getPokemonList(query: String): Flow<PagingData<PokemonResultsItem>>
}