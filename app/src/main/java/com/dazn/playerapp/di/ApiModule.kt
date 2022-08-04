package com.dazn.playerapp.di

import com.dazn.playerapp.api.PlayerApi
import com.dazn.playerapp.api.PlayerService
import com.dazn.playerapp.domain.DomainMapper
import com.dazn.playerapp.domain.GetDataUseCase
import com.jakewharton.espresso.OkHttp3IdlingResource

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

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
    fun providePlayerService(playerApi: PlayerApi): PlayerService {
        return PlayerService(playerApi)
    }

    @Provides
    fun provideEventsUseCase(playerService: PlayerService, domainMapper: DomainMapper): GetDataUseCase {
        return  GetDataUseCase(playerService, domainMapper)
    }
}