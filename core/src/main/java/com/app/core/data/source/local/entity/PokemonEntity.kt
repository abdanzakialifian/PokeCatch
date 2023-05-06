package com.app.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_entity")
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo("pokemon_id")
    val pokemonId: Int? = null,

    @ColumnInfo("pokemon_name")
    val pokemonName: String? = null,

    @ColumnInfo("pokemon_image")
    val pokemonImage: String? = null,

    @ColumnInfo("pokemon_type")
    val pokemonType: String? = null
)
