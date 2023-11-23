package com.example.marvelretro1.constants

import java.math.BigInteger
import java.security.MessageDigest

class MarvelConstants private constructor() {

    object BUNDLE {
        const val DESCRIPTION = "description"
        const val ID = "id"
        const val NAME = "name"
        const val THUMBNAIL = "thumbnail"
    }

    object BUNDLE_COMICS {
        const val DESCRIPTION = "description"
        const val ID = "id"
        const val TITLE = "title"
        const val THUMBNAIL = "thumbnail"
    }

    companion object {
        val ts = System.currentTimeMillis().toString()

        fun getHash() : String {
            val input = "$ts${MarvelConstants.KEYS.PRIVATE}${MarvelConstants.KEYS.PUBLIC}"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }

    object KEYS {
        const val PRIVATE = "922b0a6b25e4a8542c7b31b8ec21d28c0b2dec4c"
        const val PUBLIC = "7e05caae3ce507bb6998ba4ca4222cf2"
    }

    object PARAMS {
        const val API_KEY = "apikey"
        const val HASH = "hash"
        const val LIMIT = "limit"
        const val LIMIT_VAL = 100
        const val TS = "ts"
        const val NAME_STARTS_WITH = "nameStartsWith"
    }

}