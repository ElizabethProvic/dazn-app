package com.dazn.playerapp.api

import com.dazn.playerapp.di.DaggerApiComponent
import com.dazn.playerapp.model.Event
import com.dazn.playerapp.model.ScheduleItem
import io.reactivex.Single
import javax.inject.Inject

class PlayerService {

    @Inject
    lateinit var api: PlayerApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getEvents(): Single<List<Event>> {
        return api.getEvents()
    }

    fun getSchedule(): Single<List<ScheduleItem>> {
        return api.getSchedule()
    }
}