package es.rudo.rickandmortyapp.app.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.data.models.Empty
import es.rudo.rickandmortyapp.app.data.models.Error
import es.rudo.rickandmortyapp.app.data.models.Success
import es.rudo.rickandmortyapp.app.domain.usecases.GetCharactersUseCase
import es.rudo.rickandmortyapp.app.domain.usecases.ObserveCharactersUseCase
import es.rudo.rickandmortyapp.app.domain.usecases.RefreshCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

data class MainUiState(
    val error: Error = Empty,
    var charactersList: List<Character> = mutableListOf(),
    val success: Success = Success(false)
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val observeCharactersUseCase: ObserveCharactersUseCase,
    private val refreshCharactersUseCase: RefreshCharactersUseCase
) : ViewModel() {

    val mainUiState by lazy {
        MutableStateFlow(MainUiState())
    }

    fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            getCharactersUseCase().collect { result ->
                result.onSuccess { characterResult ->
                    characterResult?.results?.let { characters ->
                        setCharactersList(characters)
                    }
                }
                result.onFailure {
                    setError(it as Error)
                }
            }
        }
    }

    fun observeCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            observeCharactersUseCase().collect { result ->
                setCharactersList(result)
            }
        }
    }

    fun refreshCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = refreshCharactersUseCase()
            result.onSuccess {
                setSuccess(it)
            }
            result.onFailure {
                setError(it as Error)
            }
        }
    }

    private fun setCharactersList(charactersList: List<Character>) {
        mainUiState.update {
            it.copy(charactersList = charactersList)
        }
    }

    private fun setSuccess(success: Success) {
        mainUiState.update {
            it.copy(success = success)
        }
    }

    private fun setError(error: Error) {
        mainUiState.update {
            it.copy(error = error)
        }
    }
}
