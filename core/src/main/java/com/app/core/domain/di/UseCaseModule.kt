package com.app.core.domain.di

import com.app.core.domain.interfaces.PokemonUseCase
import com.app.core.domain.usecase.PokemonInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @ViewModelScoped
    @Binds
    abstract fun provideUseCase(pokemonInteractorImpl: PokemonInteractorImpl): PokemonUseCase
}