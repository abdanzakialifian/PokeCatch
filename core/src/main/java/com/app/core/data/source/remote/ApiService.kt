package com.app.core.data.source.remote

import com.app.core.data.source.remote.response.DetailPokemonResponse
import com.app.core.data.source.remote.response.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): Response<DetailPokemonResponse>

}