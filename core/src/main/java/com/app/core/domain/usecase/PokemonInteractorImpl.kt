package com.app.core.domain.usecase

import androidx.paging.PagingData
import com.app.core.domain.interfaces.PokemonRepository
import com.app.core.domain.interfaces.PokemonUseCase
import com.app.core.domain.model.DetailPokemon
import com.app.core.domain.model.Pokemon
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

    override fun insertPokemon(pokemon: Pokemon) {
        pokemonRepository.insertPokemon(pokemon)
    }

    override fun checkPokemon(pokemonId: Int): Boolean = pokemonRepository.checkPokemon(pokemonId)

    override fun deletePokemon(pokemonId: Int) {
        pokemonRepository.deletePokemon(pokemonId)
    }

    override fun getAllPokemon(): Flow<List<Pokemon>> = pokemonRepository.getAllPokemon()
    override fun updatePokemonName(pokemonId: Int, pokemonName: String, totalUpdate: Int): Int =
        pokemonRepository.updatePokemonName(pokemonId, pokemonName, totalUpdate)

    override fun getTotalUpdate(pokemonId: Int): Int = pokemonRepository.getTotalUpdate(pokemonId)
}