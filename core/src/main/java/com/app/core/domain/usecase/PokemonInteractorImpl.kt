package com.app.core.domain.usecase

import androidx.paging.PagingData
import com.app.core.domain.interfaces.PokemonRepository
import com.app.core.domain.interfaces.PokemonUseCase
import com.app.core.domain.model.PokemonResultsItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonInteractorImpl @Inject constructor(private val pokemonRepository: PokemonRepository) :
    PokemonUseCase {
    override fun getPokemonList(): Flow<PagingData<PokemonResultsItem>> =
        pokemonRepository.getPokemonList()
}