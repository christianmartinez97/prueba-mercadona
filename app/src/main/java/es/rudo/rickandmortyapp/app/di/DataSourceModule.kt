package es.rudo.rickandmortyapp.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rudo.rickandmortyapp.app.data.source.RemoteCharactersDataSource
import es.rudo.rickandmortyapp.app.data.source.local.LocalCharactersDataSource
import es.rudo.rickandmortyapp.app.data.source.local.impl.LocalCharactersDataSourceImpl
import es.rudo.rickandmortyapp.app.data.source.remote.impl.RemoteCharactersDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideRemoteCharactersDataSource(remoteCharactersDataSourceImpl: RemoteCharactersDataSourceImpl): RemoteCharactersDataSource

    @Binds
    abstract fun provideLocalCharactersDataSource(localCharactersDataSourceImpl: LocalCharactersDataSourceImpl): LocalCharactersDataSource
}
