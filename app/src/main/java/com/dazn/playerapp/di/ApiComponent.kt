package com.dazn.playerapp.di

import com.dazn.playerapp.api.PlayerService
import com.dazn.playerapp.events.presentation.EventsViewModel
import com.dazn.playerapp.schedule.presentation.ScheduleViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: PlayerService)

    fun inject(viewModel: EventsViewModel)

    fun inject(viewModel: ScheduleViewModel)
}