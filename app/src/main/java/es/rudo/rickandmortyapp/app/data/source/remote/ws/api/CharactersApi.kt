package es.rudo.rickandmortyapp.app.data.source.remote.ws.api

import es.rudo.rickandmortyapp.app.data.models.CharacterResult
import retrofit2.Response
import retrofit2.http.*

interface CharactersApi {

    @GET("character/")
    suspend fun getCharacters(): Response<CharacterResult>
}
