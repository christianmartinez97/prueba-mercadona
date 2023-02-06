package es.rudo.rickandmortyapp.app.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.rudo.rickandmortyapp.app.data.models.CharacterRoom

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterRoom")
    fun getAllCharacters(): List<CharacterRoom>

    @Query("SELECT * FROM CharacterRoom WHERE id = :characterId")
    fun getCharacterById(characterId: Int): CharacterRoom

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertCharacters(charactersList: List<CharacterRoom>?)
}
