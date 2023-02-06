package es.rudo.rickandmortyapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rudo.rickandmortyapp.app.data.source.local.LocalCharactersDataSource
import es.rudo.rickandmortyapp.app.data.source.local.database.AppDatabase
import es.rudo.rickandmortyapp.app.data.source.local.impl.LocalCharactersDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideLocalCharactersDataSource(appDatabase: AppDatabase): LocalCharactersDataSource =
        LocalCharactersDataSourceImpl(appDatabase.characterDao())
}
