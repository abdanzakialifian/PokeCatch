package com.app.core.domain.usecase

import androidx.paging.PagingData
import com.app.core.domain.interfaces.PokemonRepository
import com.app.core.domain.interfaces.PokemonUseCase
import com.app.core.domain.model.DetailPokemon
import com.app.core.domain.model.PokemonResultsItem
import com.app.core.utils.UiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonInteractorImpl @Inject constructor(private val pokemonRepository: PokemonRepository) :
    PokemonUseCase {
    override fun getPokemonList(query: String): Flow<PagingData<PokemonResultsItem>> =
        pokemonRepository.getPokemonList(query)

    override fun getPokemon(name: String): Flow<UiState<DetailPokemon>> =
        pokemonRepository.getPokemon(name)
}