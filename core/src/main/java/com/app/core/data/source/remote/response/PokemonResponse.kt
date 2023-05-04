package com.app.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonResponse(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<PokemonResultsItemResponse>? = null
)

data class PokemonResultsItemResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
