package com.example.marvelretro1.data
import com.example.marvelretro1.constants.MarvelConstants
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
        @Query(MarvelConstants.PARAMS.API_KEY) apiKey: String,
        @Query(MarvelConstants.PARAMS.TS) ts : String,
        @Query(MarvelConstants.PARAMS.HASH) hash: String,
        @Query(MarvelConstants.PARAMS.LIMIT) limit: Int = MarvelConstants.PARAMS.LIMIT_VAL
    ): Response<MarvelResponse>

    @GET("characters")
    suspend fun fetchCharacterNameStartsWith(
        @Query(MarvelConstants.PARAMS.API_KEY) apiKey: String,
        @Query(MarvelConstants.PARAMS.TS) ts : String,
        @Query(MarvelConstants.PARAMS.HASH) hash: String,
        @Query(MarvelConstants.PARAMS.NAME_STARTS_WITH) nameStartsWith: String,
        @Query(MarvelConstants.PARAMS.LIMIT) limit: Int = MarvelConstants.PARAMS.LIMIT_VAL
    ): Response<MarvelResponse>

    @GET("characters/{characterId}/comics")
    suspend fun fetchCharacterComics(
        @Path(
            value = "characterId",
            encoded = true
        ) characterId: Int,
        @Query(MarvelConstants.PARAMS.API_KEY) apiKey: String,
        @Query(MarvelConstants.PARAMS.TS) ts : String,
        @Query(MarvelConstants.PARAMS.HASH) hash: String
    ): Response<MarvelComicResponse>

    @GET("comics/{comicId}/characters")
    suspend fun fetchComicCharacters(
        @Path(
            value = "comicId",
            encoded = true
        ) characterId: Int,
        @Query(MarvelConstants.PARAMS.API_KEY) apiKey: String,
        @Query(MarvelConstants.PARAMS.TS) ts : String,
        @Query(MarvelConstants.PARAMS.HASH) hash: String
    ): Response<MarvelComicCharactersResponse>
}