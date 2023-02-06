package es.rudo.rickandmortyapp.app.data.source

import es.rudo.rickandmortyapp.app.data.models.CharacterResult

interface RemoteCharactersDataSource {
    suspend fun getCharacters(): CharacterResult
}
