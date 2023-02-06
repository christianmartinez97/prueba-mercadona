package es.rudo.rickandmortyapp.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rudo.rickandmortyapp.app.data.repository.CharactersRepositoryImpl
import es.rudo.rickandmortyapp.app.domain.repository.CharactersRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCharactersRepository(charactersRepositoryImpl: CharactersRepositoryImpl): CharactersRepository
}
