package com.example.marvelretro1.data.repositories

import com.example.marvelretro1.data.responses.GetCharactersResponse

interface ICharacterRepository {
    suspend fun fetchCharacters(): GetCharactersResponse
    suspend fun fetchCharactersNameStartWith(starting: String): GetCharactersResponse
}