package com.app.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.core.data.source.local.PokemonDao
import com.app.core.data.source.local.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}