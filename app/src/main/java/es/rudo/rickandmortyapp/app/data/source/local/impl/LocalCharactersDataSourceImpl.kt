package es.rudo.rickandmortyapp.app.data.source.local.impl

import es.rudo.rickandmortyapp.app.data.models.*
import es.rudo.rickandmortyapp.app.data.source.local.LocalCharactersDataSource
import es.rudo.rickandmortyapp.app.data.source.local.database.dao.CharacterDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalCharactersDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao
) : LocalCharactersDataSource {
    override suspend fun getCharacters(): List<Character> {
        return characterDao.getAllCharacters().map {
            it.toCharacter()
        }
    }

    override fun observeCharacters(): Flow<List<Character>> {
        return characterDao.observeCharacters().map { charactersRoomList ->
            charactersRoomList.map { characterRoom ->
                characterRoom.toCharacter()
            }
        }
    }

    override suspend fun getCharacterInfo(characterId: Int): Character {
        return characterDao.getCharacterById(characterId).toCharacter()
    }

    override suspend fun insertCharacters(charactersList: List<Character>) {
        characterDao.insertCharacters(
            charactersList.map {
                it.toCharacterRoom()
            }
        )
    }
}
