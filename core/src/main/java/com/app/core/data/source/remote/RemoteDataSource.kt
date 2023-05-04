package com.app.core.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.core.data.source.remote.paging.PokemonPagingSource
import com.app.core.data.source.remote.response.PokemonResultsItemResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val pokemonPagingSource: PokemonPagingSource) {
    fun getPokemonList(): Flow<PagingData<PokemonResultsItemResponse>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
        ),
        pagingSourceFactory = {
            pokemonPagingSource
        }
    ).flow
}