package com.example.marvelretro1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelretro1.data.models.comicCharacters.Result
import com.example.marvelretro1.data.repositories.ComicCharactersRepository
import com.example.marvelretro1.data.responses.GetComicCharactersFailure
import com.example.marvelretro1.data.responses.GetComicCharactersSuccess
import kotlinx.coroutines.launch

class ComicCharactersViewModel(private val comicCharactersRepository: ComicCharactersRepository) : ViewModel() {
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

    fun getCharacters(comicID: Int) {
        viewModelScope.launch {
            _loading.value = true

            val response = comicCharactersRepository.fetchCharacterComics(comicID)

            _loading.value = false

            when (response) {
                is GetComicCharactersSuccess -> {
                    _charactersComics.value = response.comicCharacters.data.results
                }

                is GetComicCharactersFailure -> {
                    _error.value = response.error.message
                }
            }
        }
    }
}