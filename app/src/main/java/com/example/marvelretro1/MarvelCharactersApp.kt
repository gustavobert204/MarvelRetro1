package com.example.marvelretro1

import android.app.Application
import com.example.marvelretro1.di.networkModule
import com.example.marvelretro1.di.repositoriesModule
import com.example.marvelretro1.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelCharactersApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MarvelCharactersApp)
            modules(
                listOf(
                    repositoriesModule,
                    viewModelsModule,
                    networkModule
                )
            )
        }

    }
}