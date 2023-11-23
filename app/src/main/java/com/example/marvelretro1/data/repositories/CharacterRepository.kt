package com.example.marvelretro1.data.repositories

import com.example.marvelretro1.data.Api
import com.example.marvelretro1.data.responses.GetCharactersFailure
import com.example.marvelretro1.data.responses.GetCharactersResponse
import com.example.marvelretro1.data.responses.GetCharactersSuccess

class CharacterRepository(private val api: Api) : ICharacterRepository {

    override suspend fun fetchCharacters(): GetCharactersResponse {
        val response = api.fetchCharacter("66dbbf76862951d4d1b3b85f63ec37fb", "1", "c3fdf37bbecef6f4c9965f4e987b2c8a")
        if (response.isSuccessful) {
            response.body()?.let {
                return GetCharactersSuccess(it)
            }
        }
        return GetCharactersFailure(Throwable(response.raw().message))
    }

    override suspend fun fetchCharactersNameStartWith(starting: String): GetCharactersResponse {
        val response = api.fetchCharacterNameStartsWith("66dbbf76862951d4d1b3b85f63ec37fb", "1", "c3fdf37bbecef6f4c9965f4e987b2c8a", starting)
        if (response.isSuccessful) {
            response.body()?.let {
                return GetCharactersSuccess(it)
            }
        }
        return GetCharactersFailure(Throwable(response.raw().message))
    }


}