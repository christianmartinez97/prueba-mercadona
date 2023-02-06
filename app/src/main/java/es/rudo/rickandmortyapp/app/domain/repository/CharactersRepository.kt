package es.rudo.rickandmortyapp.app.domain.repository

import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.data.models.CharacterResult
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getCharacters(): Flow<Result<CharacterResult>>
    fun observeCharacters(): Flow<List<Character>>
    suspend fun getCharacterInfo(characterId: Int): Flow<Result<Character>>
    suspend fun refreshCharacters(): Result<Unit>
}
