package com.app.core.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.core.data.source.remote.ApiService
import com.app.core.data.source.remote.response.PokemonResultsItemResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonPagingSource @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, PokemonResultsItemResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResultsItemResponse> {
        val offset = params.key ?: INITIAL_LOAD_SIZE

        return try {
            val response = apiService.getPokemonList(offset, params.loadSize)
            val responseBody = response.body()?.results

            LoadResult.Page(
                data = responseBody ?: emptyList(),
                prevKey = if (offset == INITIAL_LOAD_SIZE) null else offset - params.loadSize,
                nextKey = if (response.body()?.next == null) null else offset + params.loadSize,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonResultsItemResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    companion object {
        private const val INITIAL_LOAD_SIZE = 0
    }
}