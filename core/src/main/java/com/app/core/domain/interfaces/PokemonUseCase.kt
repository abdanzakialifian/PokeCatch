package com.app.core.domain.interfaces

import androidx.paging.PagingData
import com.app.core.domain.model.DetailPokemon
import com.app.core.domain.model.Pokemon
import com.app.core.domain.model.PokemonResultsItem
import com.app.core.utils.UiState
import kotlinx.coroutines.flow.Flow

interface PokemonUseCase {
    fun getPokemonList(query: String): Flow<PagingData<PokemonResultsItem>>
    fun getPokemon(name: String): Flow<UiState<DetailPokemon>>
    fun insertPokemon(pokemon: Pokemon)
    fun checkPokemon(pokemonId: Int): Boolean
    fun deletePokemon(pokemonId: Int)
    fun getAllPokemon(): Flow<List<Pokemon>>
    fun updatePokemonName(pokemonId: Int, pokemonName: String, totalUpdate: Int): Int
    fun getTotalUpdate(pokemonId: Int): Int
}