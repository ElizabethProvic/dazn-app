package com.dazn.playerapp.di

import com.dazn.playerapp.api.PlayerApi
import com.dazn.playerapp.api.PlayerService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://us-central1-dazn-sandbox.cloudfunctions.net/"

    @Provides
    fun provideApi(): PlayerApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PlayerApi::class.java)
    }

    @Provides
    fun providePlayerService(): PlayerService {
        return PlayerService()
    }
}