package com.example.marvelretro1.data.repositories

import com.example.marvelretro1.data.Api
import com.example.marvelretro1.data.responses.GetComicCharactersFailure
import com.example.marvelretro1.data.responses.GetComicCharactersResponse
import com.example.marvelretro1.data.responses.GetComicCharactersSuccess

class ComicCharactersRepository(private val api: Api) : IComicCharactersRepository {

    override suspend fun fetchCharacterComics(comicID : Int): GetComicCharactersResponse {
        val response = api.fetchComicCharacters(comicID, "66dbbf76862951d4d1b3b85f63ec37fb", "1", "c3fdf37bbecef6f4c9965f4e987b2c8a")
        if (response.isSuccessful) {
            response.body()?.let {
                return GetComicCharactersSuccess(it)
            }
        }
        return GetComicCharactersFailure(Throwable(response.raw().message))
    }

}