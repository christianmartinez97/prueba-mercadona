package es.rudo.rickandmortyapp.app.domain.usecases

import es.rudo.rickandmortyapp.app.data.models.CharacterResult
import es.rudo.rickandmortyapp.app.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    operator fun invoke(): Flow<Result<CharacterResult?>> {
        return charactersRepository.getCharacters()
    }
}
