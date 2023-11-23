package com.example.marvelretro1.di

import com.example.marvelretro1.data.Api
import com.example.marvelretro1.data.repositories.CharacterComicsRepository
import com.example.marvelretro1.data.repositories.CharacterRepository
import com.example.marvelretro1.data.repositories.ICharacterComicsRepository
import com.example.marvelretro1.data.repositories.ICharacterRepository
import com.example.marvelretro1.ui.CharacterComicsViewModel
import com.example.marvelretro1.ui.CharactersViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



val repositoriesModule = module {
    singleOf(::CharacterRepository) { bind<ICharacterRepository>() }
    singleOf(::CharacterComicsRepository) { bind<ICharacterComicsRepository>() }
}

val viewModelsModule = module {
    viewModelOf(::CharactersViewModel)
    viewModel { CharacterComicsViewModel(get()) }
}

val networkModule = module {

    singleOf(::OkHttpClient) {
        provideOkHttpClient()
    }
    single<Retrofit> {
        provideRetrofit(get())
    }

    single<Api> {
        provideApi(get())
    }

}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://gateway.marvel.com:443/v1/public/").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)