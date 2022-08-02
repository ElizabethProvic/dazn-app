package com.dazn.playerapp.di

import com.dazn.playerapp.events.ui.EventsContract
import com.dazn.playerapp.events.ui.EventsPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class EventsModule {

    @Binds
    abstract fun bindsEventsPresenter(eventsPresenter: EventsPresenter): EventsContract.Presenter
}