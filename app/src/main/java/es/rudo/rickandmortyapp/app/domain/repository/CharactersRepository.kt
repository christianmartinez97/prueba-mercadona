package es.rudo.rickandmortyapp.app.domain.repository

import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.data.models.CharacterResult
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getCharacters(page: Int): Flow<Result<CharacterResult?>>
    suspend fun getCharacterInfo(characterId: Int): Flow<Result<Character?>>
}
