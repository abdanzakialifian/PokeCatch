package com.app.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.app.core.data.source.remote.RemoteDataSource
import com.app.core.domain.interfaces.PokemonRepository
import com.app.core.domain.model.DetailPokemon
import com.app.core.domain.model.PokemonResultsItem
import com.app.core.utils.UiState
import com.app.core.utils.mapToDetailPokemon
import com.app.core.utils.mapToPokemonResultsItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
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
}