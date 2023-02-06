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

class CharactersRepositoryImpl constructor(
    private val remoteCharactersDataSource: RemoteCharactersDataSource,
    private val localCharactersDataSource: LocalCharactersDataSource
) : CharactersRepository {

    override suspend fun getCharacters(page: Int): Flow<Result<CharacterResult?>> {
        return flow {
            try {
                val result = remoteCharactersDataSource.getCharacters()
                localCharactersDataSource.insertCharacters(result?.results)
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

    override suspend fun getCharacterInfo(characterId: Int): Flow<Result<Character?>> {
        return flow {
            try {
                emit(Result.success(remoteCharactersDataSource.getCharacterInfo(characterId)))
            } catch (ex: Exception) {
                val error = getError(ex)
                if (error is Error.NetworkException) {
                    emit(Result.success(localCharactersDataSource.getCharacterInfo(characterId)))
                } else {
                    emit(Result.failure(error))
                }
            }
        }
    }
}
