package es.rudo.rickandmortyapp.app.data.source.remote.impl

import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.data.models.CharacterResult
import es.rudo.rickandmortyapp.app.data.models.Error
import es.rudo.rickandmortyapp.app.data.source.RemoteCharactersDataSource
import es.rudo.rickandmortyapp.app.data.source.remote.ws.api.CharactersApi
import javax.inject.Inject

class RemoteCharactersDataSourceImpl @Inject constructor(
    private val charactersApi: CharactersApi
) : RemoteCharactersDataSource {

    override suspend fun getCharacters(): CharacterResult {
        return charactersApi.getCharacters().body() ?: kotlin.run {
            throw Error.EmptyBody("Empty body")
        }
    }

    override suspend fun getCharacterInfo(characterId: Int): Character {
        return charactersApi.getCharacterInfo(characterId).body() ?: kotlin.run {
            throw Error.EmptyBody("Empty body")
        }
    }
}
