package es.rudo.rickandmortyapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rudo.rickandmortyapp.app.domain.repository.CharactersRepository
import es.rudo.rickandmortyapp.app.domain.usecases.GetCharacterInfoUseCase
import es.rudo.rickandmortyapp.app.domain.usecases.GetCharactersUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(
        charactersRepository: CharactersRepository
    ): GetCharactersUseCase = GetCharactersUseCase(charactersRepository)

    @Provides
    @Singleton
    fun provideGetCharacterInfoUseCase(
        charactersRepository: CharactersRepository
    ): GetCharacterInfoUseCase = GetCharacterInfoUseCase(charactersRepository)
}
