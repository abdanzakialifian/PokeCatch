package com.app.core.utils

import com.app.core.data.source.remote.response.PokemonResultsItemResponse
import com.app.core.domain.model.PokemonResultsItem

fun PokemonResultsItemResponse.mapToPokemonResultsItem(): PokemonResultsItem = PokemonResultsItem(
    name = this.name,
    url = this.url
)