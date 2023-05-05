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

    private var querySearch = ""

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResultsItemResponse> {
        val offset = params.key ?: INITIAL_LOAD_SIZE

        return try {
            val loadSize = if (querySearch != "") SEARCH_LOAD_SIZE else params.loadSize

            val response = apiService.getPokemonList(offset, loadSize)
            val responseBody = response.body()?.results

            val filterList = if (querySearch != "") responseBody?.filter { data ->
                data.name?.contains(
                    querySearch,
                    true
                ) ?: false
            } else responseBody

            LoadResult.Page(
                data = filterList ?: emptyList(),
                prevKey = if (offset == INITIAL_LOAD_SIZE) null else offset - loadSize,
                nextKey = if (response.body()?.next == null) null else offset + loadSize,
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

    fun setQuerySearch(query: String) {
        this.querySearch = query
    }

    companion object {
        private const val INITIAL_LOAD_SIZE = 0
        private const val SEARCH_LOAD_SIZE = 100
    }
}