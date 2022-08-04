package com.dazn.playerapp.api

import com.dazn.playerapp.model.Event
import com.dazn.playerapp.model.ScheduleItem
import io.reactivex.Single
import javax.inject.Inject

class PlayerService @Inject constructor(
    private val api: PlayerApi
) {

    fun getEvents(): Single<List<Event>> {
        return api.getEvents()
    }

    fun getSchedule(): Single<List<ScheduleItem>> {
        return api.getSchedule()
    }
}