package com.app.pokecatch.presentation.home.viewmodel

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.core.domain.interfaces.PokemonUseCase
import com.app.core.domain.model.PokemonResultsItem
import com.app.pokecatch.utils.getQueryTextChangeStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(pokemonUseCase: PokemonUseCase) : ViewModel() {
    private val searchQuery = MutableStateFlow("")

    fun setQuery(searchQuery: String) {
        this.searchQuery.value = searchQuery
    }

    @OptIn(FlowPreview::class)
    fun searchFlow(searchView: SearchView): Flow<String> =
        searchView.getQueryTextChangeStateFlow()
            .debounce(600L)
            .distinctUntilChanged()
            .flowOn(Dispatchers.Default)

    @OptIn(ExperimentalCoroutinesApi::class)
    val getPokemonList: Flow<PagingData<PokemonResultsItem>> =
        searchQuery.flatMapLatest { searchQuery ->
            pokemonUseCase.getPokemonList(searchQuery).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = PagingData.empty()
            ).cachedIn(viewModelScope)
        }
}