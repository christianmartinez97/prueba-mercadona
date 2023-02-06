package es.rudo.rickandmortyapp.app.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.rudo.rickandmortyapp.app.data.models.CharacterRoom
import es.rudo.rickandmortyapp.app.data.source.local.database.dao.CharacterDao

@Database(
    entities = [CharacterRoom::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
