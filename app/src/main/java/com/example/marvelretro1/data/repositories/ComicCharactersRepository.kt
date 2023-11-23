package com.example.marvelretro1.data.repositories

import com.example.marvelretro1.constants.MarvelConstants
import com.example.marvelretro1.data.Api
import com.example.marvelretro1.data.responses.GetComicCharactersFailure
import com.example.marvelretro1.data.responses.GetComicCharactersResponse
import com.example.marvelretro1.data.responses.GetComicCharactersSuccess

class ComicCharactersRepository(private val api: Api) : IComicCharactersRepository {

    override suspend fun fetchCharacterComics(comicID: Int): GetComicCharactersResponse {
        val response = api.fetchComicCharacters(
            comicID,
            MarvelConstants.KEYS.PUBLIC,
            MarvelConstants.ts,
            MarvelConstants.getHash()
        )
        if (response.isSuccessful) {
            response.body()?.let {
                return GetComicCharactersSuccess(it)
            }
        }
        return GetComicCharactersFailure(Throwable(response.raw().message))
    }

}