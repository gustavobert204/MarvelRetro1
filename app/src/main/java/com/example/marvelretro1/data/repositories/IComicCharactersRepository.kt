package com.example.marvelretro1.data.repositories

import com.example.marvelretro1.data.responses.GetComicCharactersResponse

interface IComicCharactersRepository {
    suspend fun fetchCharacterComics(comicID : Int): GetComicCharactersResponse
}