package es.rudo.rickandmortyapp.app.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.rudo.rickandmortyapp.app.data.source.local.database.AppDatabase
import es.rudo.rickandmortyapp.app.data.source.local.database.dao.CharacterDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "Rick&Morty.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao {
        return appDatabase.characterDao()
    }
}
