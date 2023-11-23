package com.example.marvelretro1.data.repositories

import com.example.marvelretro1.data.responses.GetCharacterComicsResponse

interface ICharacterComicsRepository {
    suspend fun fetchCharacterComics(characterID : Int): GetCharacterComicsResponse
}