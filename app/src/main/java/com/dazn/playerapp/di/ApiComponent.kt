package com.dazn.playerapp.di

import com.dazn.playerapp.api.PlayerService
import com.dazn.playerapp.events.domain.EventsDataUseCase
import com.dazn.playerapp.events.ui.EventsPresenter
import com.dazn.playerapp.schedule.ui.SchedulePresenter
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: PlayerService)

    fun inject(useCase: EventsDataUseCase)

    fun inject(presenter: EventsPresenter)

    fun inject(presenter: SchedulePresenter)
}

