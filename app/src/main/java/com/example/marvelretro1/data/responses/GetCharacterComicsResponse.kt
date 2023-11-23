package com.example.marvelretro1.data.responses

import com.example.marvelretro1.data.models.charactercomics.MarvelComicResponse

sealed class GetCharacterComicsResponse
class GetCharacterComicsSuccess(val characterComics: MarvelComicResponse) : GetCharacterComicsResponse()
class GetCharacterComicsFailure(val error: Throwable) : GetCharacterComicsResponse()
