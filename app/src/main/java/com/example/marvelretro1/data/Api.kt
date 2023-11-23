package com.example.marvelretro1.data
import com.example.marvelretro1.data.models.charactercomics.MarvelComicResponse
import com.example.marvelretro1.data.models.comicCharacters.MarvelComicCharactersResponse
import com.example.marvelretro1.modeladoClase.MarvelResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("characters")
    suspend fun fetchCharacter(
        @Query("apikey") apiKey: String,
        @Query("ts") ts : String,
        @Query("hash") hash: String
    ): Response<MarvelResponse>

    @GET("characters")
    suspend fun fetchCharacterNameStartsWith(
        @Query("apikey") apiKey: String,
        @Query("ts") ts : String,
        @Query("hash") hash: String,
        @Query("nameStartsWith") nameStartsWith: String
    ): Response<MarvelResponse>

    @GET("characters/{characterId}/comics")
    suspend fun fetchCharacterComics(
        @Path(
            value = "characterId",
            encoded = true
        ) characterId: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") ts : String,
        @Query("hash") hash: String
    ): Response<MarvelComicResponse>

    @GET("comics/{comicId}/characters")
    suspend fun fetchComicCharacters(
        @Path(
            value = "comicId",
            encoded = true
        ) characterId: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") ts : String,
        @Query("hash") hash: String
    ): Response<MarvelComicCharactersResponse>
}