package es.rudo.rickandmortyapp.app.data.source.local.impl

import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.data.models.toCharacter
import es.rudo.rickandmortyapp.app.data.models.toCharactersList
import es.rudo.rickandmortyapp.app.data.models.toRoomCharactersList
import es.rudo.rickandmortyapp.app.data.source.local.LocalCharactersDataSource
import es.rudo.rickandmortyapp.app.data.source.local.database.dao.CharacterDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalCharactersDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao
) : LocalCharactersDataSource {
    override fun getCharacters(): List<Character> {
        return characterDao.getAllCharacters().toCharactersList()
    }

    override fun observeCharacters(): Flow<List<Character>> {
        return characterDao.observeCharacters().map {
            it.toCharactersList()
        }
    }

    override fun getCharacterInfo(characterId: Int): Character {
        return characterDao.getCharacterById(characterId).toCharacter()
    }

    override fun insertCharacters(charactersList: List<Character>?) {
        characterDao.insertCharacters(charactersList.toRoomCharactersList())
    }
}
