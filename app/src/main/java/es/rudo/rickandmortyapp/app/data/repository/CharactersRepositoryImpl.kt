package es.rudo.rickandmortyapp.app.data.repository

import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.data.models.CharacterResult
import es.rudo.rickandmortyapp.app.data.models.Error
import es.rudo.rickandmortyapp.app.data.source.RemoteCharactersDataSource
import es.rudo.rickandmortyapp.app.data.source.local.LocalCharactersDataSource
import es.rudo.rickandmortyapp.app.domain.repository.CharactersRepository
import es.rudo.rickandmortyapp.app.helpers.Utils.getError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteCharactersDataSource: RemoteCharactersDataSource,
    private val localCharactersDataSource: LocalCharactersDataSource
) : CharactersRepository {

    override fun getCharacters(): Flow<Result<CharacterResult>> {
        return flow {
            try {
                val result = remoteCharactersDataSource.getCharacters()
                localCharactersDataSource.insertCharacters(result.results)
                emit(Result.success(result))
            } catch (ex: Exception) {
                val error = getError(ex)
                if (error is Error.NetworkException) {
                    val result = localCharactersDataSource.getCharacters()
                    emit(Result.success(CharacterResult(results = result)))
                } else {
                    emit(Result.failure(error))
                }
            }
        }
    }

    override fun observeCharacters(): Flow<List<Character>> {
        return localCharactersDataSource.observeCharacters()
    }

    override suspend fun refreshCharacters(): Result<Unit> {
        return try {
            remoteCharactersDataSource.getCharacters().also {
                localCharactersDataSource.insertCharacters(it.results)
            }
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(getError(ex))
        }
    }

    override suspend fun getCharacterInfo(characterId: Int): Character {
        return localCharactersDataSource.getCharacterInfo(characterId)
    }
}
