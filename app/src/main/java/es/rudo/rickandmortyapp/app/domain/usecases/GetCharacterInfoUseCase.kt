package es.rudo.rickandmortyapp.app.domain.usecases

import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterInfoUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    operator fun invoke(characterId: Int): Flow<Result<Character?>> {
        return charactersRepository.getCharacterInfo(characterId)
    }
}
