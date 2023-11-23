package com.example.marvelretro1.data.models.charactercomics

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)