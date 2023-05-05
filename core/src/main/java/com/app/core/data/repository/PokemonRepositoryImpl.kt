package com.app.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.app.core.data.source.remote.RemoteDataSource
import com.app.core.domain.interfaces.PokemonRepository
import com.app.core.domain.model.PokemonResultsItem
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
}