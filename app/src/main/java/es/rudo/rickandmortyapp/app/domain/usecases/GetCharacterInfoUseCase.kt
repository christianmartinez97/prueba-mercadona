package es.rudo.rickandmortyapp.app.domain.usecases

import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharacterInfoUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(characterId: Int): Character {
        return charactersRepository.getCharacterInfo(characterId)
    }
}
