package com.example.marvelretro1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelretro1.data.repositories.CharacterComicsRepository
import com.example.marvelretro1.data.responses.GetCharacterComicsFailure
import com.example.marvelretro1.data.responses.GetCharacterComicsSuccess
import com.example.marvelretro1.data.models.charactercomics.Result
import kotlinx.coroutines.launch

class CharacterComicsViewModel(private val characterComicsRepository: CharacterComicsRepository) : ViewModel() {
    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _charactersComics = MutableLiveData<List<Result>>()
    val charactersComics: LiveData<List<Result>> get() = _charactersComics

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    /*
    init {
        getCharacters()
    }*/

    fun getCharacters(characterID: Int) {
        viewModelScope.launch {
            _loading.value = true

            val response = characterComicsRepository.fetchCharacterComics(characterID)

            _loading.value = false

            when (response) {
                is GetCharacterComicsSuccess -> {
                    _charactersComics.value = response.characterComics.data.results
                }
                is GetCharacterComicsFailure -> {
                    _error.value = response.error.message
                }
            }
        }
    }
}