package es.rudo.rickandmortyapp.app.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.data.models.Empty
import es.rudo.rickandmortyapp.app.data.models.Error
import es.rudo.rickandmortyapp.app.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

data class MainUiState(
    val error: Error = Empty,
    var charactersList: List<Character> = mutableListOf()
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    val mainUiState by lazy {
        MutableStateFlow(MainUiState())
    }

    fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            getCharactersUseCase().collect { result ->
                result.onSuccess { characterResult ->
                    characterResult?.results?.let { characters ->
                        (mainUiState.value.charactersList as MutableList<Character>).addAll(
                            characters
                        )
                    }
                }
                result.onFailure {
                    setError(it as Error)
                }
            }
        }
    }

    private fun setError(error: Error) {
        mainUiState.update {
            it.copy(error = error)
        }
    }
}
