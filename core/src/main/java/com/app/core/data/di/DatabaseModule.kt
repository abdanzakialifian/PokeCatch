package com.app.core.data.di

import android.content.Context
import androidx.room.Room
import com.app.core.data.source.local.PokemonDao
import com.app.core.data.source.local.database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providePokemonDatabase(@ApplicationContext context: Context): PokemonDatabase =
        Room.databaseBuilder(context, PokemonDatabase::class.java, "pokemon_db")
            .fallbackToDestructiveMigration().build()

    @Provides
    fun providePokemonDao(pokemonDatabase: PokemonDatabase): PokemonDao =
        pokemonDatabase.pokemonDao()
}