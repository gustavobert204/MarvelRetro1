package com.example.marvelretro1.constants

class MarvelConstants private constructor() {

    object BUNDLE {
        const val DESCRIPTION = "description"
        const val ID = "id"
        const val NAME = "name"
        const val THUMBNAIL = "thumbnail"
    }

    object BUNDLE_COMICS {
        const val ID = "id"
        const val TITLE = "title"
        const val THUMBNAIL = "thumbnail"
        const val DESCRIPTION = "description"
    }

    object HTTP {
        const val SUCCESS = 200
    }

    object KEYS {
        const val PRIVATE = "824cda070ea3b910e499d4d4ba38b85c7457ac6e"
        const val PUBLIC = "407e6dc24202527c7bda332d58eae892"
    }

    object PARAMS {
        const val API_KEY = "apikey"
        const val HASH = "hash"
        const val LIMIT = "limit"
        const val OFFSET = "offset"
        const val OFFSET_VAL = 6
        const val TS = "ts"
    }

}