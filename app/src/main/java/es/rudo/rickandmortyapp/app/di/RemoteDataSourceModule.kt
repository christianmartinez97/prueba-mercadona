package es.rudo.rickandmortyapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rudo.rickandmortyapp.app.data.source.RemoteCharactersDataSource
import es.rudo.rickandmortyapp.app.data.source.remote.impl.RemoteCharactersDataSourceImpl
import es.rudo.rickandmortyapp.app.data.source.remote.ws.api.CharactersApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteCharactersDataSource(charactersApi: CharactersApi): RemoteCharactersDataSource =
        RemoteCharactersDataSourceImpl(charactersApi)
}
