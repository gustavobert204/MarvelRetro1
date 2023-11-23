package com.example.marvelretro1.data.repositories

import com.example.marvelretro1.data.Api
import com.example.marvelretro1.data.responses.GetCharacterComicsFailure
import com.example.marvelretro1.data.responses.GetCharacterComicsResponse
import com.example.marvelretro1.data.responses.GetCharacterComicsSuccess


class CharacterComicsRepository(private val api: Api) : ICharacterComicsRepository {

    override suspend fun fetchCharacterComics(characterID : Int): GetCharacterComicsResponse {
        val response = api.fetchCharacterComics(characterID, "66dbbf76862951d4d1b3b85f63ec37fb", "1", "c3fdf37bbecef6f4c9965f4e987b2c8a")
        if (response.isSuccessful) {
            response.body()?.let {
                return GetCharacterComicsSuccess(it)
            }
        }
        return GetCharacterComicsFailure(Throwable(response.raw().message))
    }

}