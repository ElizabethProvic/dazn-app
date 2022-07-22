package com.dazn.playerapp.events.domain

import com.dazn.playerapp.api.PlayerService
import com.dazn.playerapp.di.DaggerApiComponent
import com.dazn.playerapp.model.Event
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventsDataUseCase {

    @Inject
    lateinit var playerService: PlayerService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val mapper = DomainMapper()

    fun getEventsData(): Single<List<Event>> {
        return playerService.getEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map {
                it.map { item -> mapper.mapEventsItemToDomain(item) }
            }
    }

    fun getScheduleData(): Single<List<Event>> {
        return playerService.getSchedule()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map {
                it.map { item -> mapper.mapScheduleItemItemToDomain(item) }
            }
    }
}