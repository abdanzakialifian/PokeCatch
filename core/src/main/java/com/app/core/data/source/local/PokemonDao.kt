package com.app.core.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.core.data.source.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemonEntity: PokemonEntity)

    @Query("SELECT EXISTS (SELECT * FROM pokemon_entity WHERE pokemon_id=:pokemonId)")
    fun checkPokemon(pokemonId: Int): Boolean

    @Query("DELETE FROM pokemon_entity WHERE pokemon_id=:pokemonId")
    fun deletePokemon(pokemonId: Int)

    @Query("SELECT * FROM pokemon_entity")
    fun getAllPokemon(): Flow<List<PokemonEntity>>
}