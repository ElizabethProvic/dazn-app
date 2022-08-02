package com.dazn.playerapp.domain

import com.dazn.playerapp.api.PlayerService
import com.dazn.playerapp.model.Event
import com.dazn.playerapp.model.ScheduleItem
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetDataUseCase @Inject constructor(
    private val playerService: PlayerService,
    private val mapper: DomainMapper
) {

    fun getEventsData(): Single<List<Event>> {
        return playerService.getEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map {
                it.map { item -> mapper.mapEventsItemToDomain(item) }
            }
    }

    fun getScheduleData(): Single<List<ScheduleItem>> {
        return playerService.getSchedule()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map {
                it.map { item -> mapper.mapScheduleItemItemToDomain(item) }
            }
    }
}