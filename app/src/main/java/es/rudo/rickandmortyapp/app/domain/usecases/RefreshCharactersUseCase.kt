package es.rudo.rickandmortyapp.app.domain.usecases

import es.rudo.rickandmortyapp.app.domain.repository.CharactersRepository
import javax.inject.Inject

class RefreshCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return charactersRepository.refreshCharacters()
    }
}
