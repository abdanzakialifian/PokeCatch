package com.app.core.data.source.local

import com.app.core.data.source.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val pokemonDao: PokemonDao) {
    fun insertPokemon(pokemonEntity: PokemonEntity) {
        pokemonDao.insertPokemon(pokemonEntity)
    }

    fun checkPokemon(pokemonId: Int): Boolean = pokemonDao.checkPokemon(pokemonId)

    fun deletePokemon(pokemonId: Int) {
        pokemonDao.deletePokemon(pokemonId)
    }

    fun getAllPokemon(): Flow<List<PokemonEntity>> = pokemonDao.getAllPokemon()

    fun updatePokemonName(pokemonId: Int, pokemonName: String, totalUpdate: Int): Int =
        pokemonDao.updatePokemonName(pokemonId, pokemonName, totalUpdate)

    fun getTotalUpdate(pokemonId: Int): Int = pokemonDao.getTotalUpdate(pokemonId)
}