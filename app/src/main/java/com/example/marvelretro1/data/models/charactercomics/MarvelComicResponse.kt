package com.example.marvelretro1.data.models.charactercomics

data class MarvelComicResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)