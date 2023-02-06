package es.rudo.rickandmortyapp.app.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.rudo.rickandmortyapp.app.data.models.CharacterRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterRoom")
    fun getAllCharacters(): List<CharacterRoom>

    @Query("SELECT * FROM CharacterRoom")
    fun observeCharacters(): Flow<List<CharacterRoom>>

    @Query("SELECT * FROM CharacterRoom WHERE id = :characterId")
    fun getCharacterById(characterId: Int): CharacterRoom

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(charactersList: List<CharacterRoom>?)
}
