package es.rudo.rickandmortyapp.app.data.source.local

import es.rudo.rickandmortyapp.app.data.models.Character
import kotlinx.coroutines.flow.Flow

interface LocalCharactersDataSource {
    fun getCharacters(): List<Character>
    fun observeCharacters(): Flow<List<Character>>
    fun getCharacterInfo(characterId: Int): Character
    fun insertCharacters(charactersList: List<Character>?)
}
