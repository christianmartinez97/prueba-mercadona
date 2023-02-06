package es.rudo.rickandmortyapp.app.ui.character_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.domain.usecases.GetCharacterInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CharacterDetailUIState(
    val characterId: Int = -1,
    val character: Character = Character()
)

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterInfoUseCase: GetCharacterInfoUseCase
) : ViewModel() {

    val characterDetailUIState by lazy {
        MutableStateFlow(CharacterDetailUIState())
    }

    fun getCharacterInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            setCharacter(getCharacterInfoUseCase(characterDetailUIState.value.characterId))
        }
    }

    private fun setCharacter(character: Character) {
        characterDetailUIState.update {
            it.copy(character = character)
        }
    }

    fun setCharacterId(characterId: Int) {
        characterDetailUIState.update {
            it.copy(characterId = characterId)
        }
    }
}
