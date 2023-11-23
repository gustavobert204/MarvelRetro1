package com.example.marvelretro1.data.responses

import com.example.marvelretro1.data.models.comicCharacters.MarvelComicCharactersResponse

sealed class GetComicCharactersResponse
class GetComicCharactersSuccess(val comicCharacters: MarvelComicCharactersResponse) : GetComicCharactersResponse()
class GetComicCharactersFailure(val error: Throwable) : GetComicCharactersResponse()
