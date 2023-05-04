package com.app.core.data.di

import com.app.core.data.repository.PokemonRepositoryImpl
import com.app.core.domain.interfaces.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository
}