package com.app.core.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.core.data.source.remote.paging.PokemonPagingSource
import com.app.core.data.source.remote.response.DetailPokemonResponse
import com.app.core.data.source.remote.response.PokemonResultsItemResponse
import com.app.core.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val pokemonPagingSource: PokemonPagingSource
) {
    fun getPokemonList(query: String): Flow<PagingData<PokemonResultsItemResponse>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
        ),
        pagingSourceFactory = {
            pokemonPagingSource.apply {
                setQuerySearch(query)
            }
        }
    ).flow

    fun getPokemon(name: String): Flow<UiState<DetailPokemonResponse>> = flow {
        val response = apiService.getPokemon(name)
        val responseBody = response.body()
        emit(UiState.Loading)
        if (response.isSuccessful && responseBody != null) {
            emit(UiState.Success(responseBody))
        } else {
            emit(UiState.Error(response.message()))
        }
    }.flowOn(Dispatchers.IO)
}