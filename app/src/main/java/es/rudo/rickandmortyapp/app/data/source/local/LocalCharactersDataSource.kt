package es.rudo.rickandmortyapp.app.data.source.local

import es.rudo.rickandmortyapp.app.data.models.Character
import kotlinx.coroutines.flow.Flow

interface LocalCharactersDataSource {
    suspend fun getCharacters(): List<Character>
    fun observeCharacters(): Flow<List<Character>>
    suspend fun getCharacterInfo(characterId: Int): Character
    suspend fun insertCharacters(charactersList: List<Character>)
}
