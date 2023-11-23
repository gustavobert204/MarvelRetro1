package com.example.marvelretro1.data.responses

import com.example.marvelretro1.modeladoClase.MarvelResponse

sealed class GetCharactersResponse
class GetCharactersSuccess(val characters: MarvelResponse) : GetCharactersResponse()
class GetCharactersFailure(val error: Throwable) : GetCharactersResponse()
