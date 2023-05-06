package com.app.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.app.core.data.source.local.LocalDataSource
import com.app.core.data.source.remote.RemoteDataSource
import com.app.core.domain.interfaces.PokemonRepository
import com.app.core.domain.model.DetailPokemon
import com.app.core.domain.model.Pokemon
import com.app.core.domain.model.PokemonResultsItem
import com.app.core.utils.UiState
import com.app.core.utils.mapToDetailPokemon
import com.app.core.utils.mapToPokemon
import com.app.core.utils.mapToPokemonEntity
import com.app.core.utils.mapToPokemonResultsItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    PokemonRepository {
    override fun getPokemonList(query: String): Flow<PagingData<PokemonResultsItem>> =
        remoteDataSource.getPokemonList(query).map { pagingData ->
            pagingData.map { map ->
                map.mapToPokemonResultsItem()
            }
        }

    override fun getPokemon(name: String): Flow<UiState<DetailPokemon>> =
        remoteDataSource.getPokemon(name).map { uiState ->
            when (uiState) {
                is UiState.Loading -> UiState.Loading
                is UiState.Success -> UiState.Success(uiState.data.mapToDetailPokemon())
                is UiState.Error -> UiState.Error(uiState.message)
            }
        }

    override fun insertPokemon(pokemon: Pokemon) {
        localDataSource.insertPokemon(pokemon.mapToPokemonEntity())
    }

    override fun checkPokemon(pokemonId: Int): Boolean = localDataSource.checkPokemon(pokemonId)
    override fun deletePokemon(pokemonId: Int) {
        localDataSource.deletePokemon(pokemonId)
    }

    override fun getAllPokemon(): Flow<List<Pokemon>> =
        localDataSource.getAllPokemon().map { pokemonEntity ->
            pokemonEntity.map {
                it.mapToPokemon()
            }
        }

    override fun updatePokemonName(pokemonId: Int, pokemonName: String, totalUpdate: Int): Int =
        localDataSource.updatePokemonName(pokemonId, pokemonName, totalUpdate)

    override fun getTotalUpdate(pokemonId: Int): Int = localDataSource.getTotalUpdate(pokemonId)
}