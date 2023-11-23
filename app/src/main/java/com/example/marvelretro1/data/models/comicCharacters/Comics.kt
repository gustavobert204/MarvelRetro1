package com.example.marvelretro1.data.models.comicCharacters

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)