package com.example.marvelretro1.data.models.comicCharacters

data class MarvelComicCharactersResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)