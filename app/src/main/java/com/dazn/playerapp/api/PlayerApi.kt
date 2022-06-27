package com.dazn.playerapp.api

import com.dazn.playerapp.model.Event
import io.reactivex.Single
import retrofit2.http.GET

interface PlayerApi {

    @GET("getEvents")
    fun getEvents(): Single<List<Event>>

    @GET("getSchedule")
    fun getSchedule(): Single<List<Event>>
}