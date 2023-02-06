package es.rudo.rickandmortyapp.app.data.source.local

import es.rudo.rickandmortyapp.app.data.models.Character

interface LocalCharactersDataSource {
    fun getCharacters(): List<Character>
    fun getCharacterInfo(characterId: Int): Character
    fun insertCharacters(charactersList: List<Character>?)
}
