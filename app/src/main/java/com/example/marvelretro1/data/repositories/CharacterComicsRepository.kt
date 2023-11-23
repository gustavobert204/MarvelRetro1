package com.example.marvelretro1.data.repositories

import com.example.marvelretro1.constants.MarvelConstants
import com.example.marvelretro1.data.Api
import com.example.marvelretro1.data.responses.GetCharacterComicsFailure
import com.example.marvelretro1.data.responses.GetCharacterComicsResponse
import com.example.marvelretro1.data.responses.GetCharacterComicsSuccess


class CharacterComicsRepository(private val api: Api) : ICharacterComicsRepository {

    override suspend fun fetchCharacterComics(characterID: Int): GetCharacterComicsResponse {
        val response = api.fetchCharacterComics(
            characterID,
            MarvelConstants.KEYS.PUBLIC,
            MarvelConstants.ts,
            MarvelConstants.getHash()
        )
        if (response.isSuccessful) {
            response.body()?.let {
                return GetCharacterComicsSuccess(it)
            }
        }
        return GetCharacterComicsFailure(Throwable(response.raw().message))
    }

}