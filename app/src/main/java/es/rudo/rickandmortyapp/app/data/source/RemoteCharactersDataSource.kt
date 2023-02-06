package es.rudo.rickandmortyapp.app.data.source

import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.data.models.CharacterResult

interface RemoteCharactersDataSource {
    suspend fun getCharacters(): CharacterResult?
    suspend fun getCharacterInfo(characterId: Int): Character?
}
