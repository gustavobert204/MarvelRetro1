package com.example.marvelretro1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelretro1.data.repositories.CharacterRepository
import com.example.marvelretro1.data.responses.GetCharactersFailure
import com.example.marvelretro1.data.responses.GetCharactersSuccess
import com.example.marvelretro1.modeladoClase.Result
import kotlinx.coroutines.launch

class CharactersViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _characters = MutableLiveData<List<Result>>()
    val characters: LiveData<List<Result>> get() = _characters

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            _loading.value = true

            val response = characterRepository.fetchCharacters()

            _loading.value = false

            when (response) {
                is GetCharactersSuccess -> {
                    _characters.value = response.characters.data.results
                }
                is GetCharactersFailure -> {
                    _error.value = response.error.message
                }
            }
        }
    }

    fun getCharactersNameStartWith(starting: String) {
        viewModelScope.launch {
            _loading.value = true

            val response = characterRepository.fetchCharactersNameStartWith(starting)

            _loading.value = false

            when (response) {
                is GetCharactersSuccess -> {
                    _characters.value = response.characters.data.results
                }
                is GetCharactersFailure -> {
                    _error.value = response.error.message
                }
            }
        }
    }

    fun filterCharacters(newText: String): List<Result> {
        if(newText.isNotEmpty()) {
            getCharactersNameStartWith(newText)
        } else {
            getCharacters()
        }
        return characters.value ?: emptyList()
    }
}