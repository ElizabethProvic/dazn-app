package com.dazn.playerapp.di

import com.dazn.playerapp.ui.events.EventsContract
import com.dazn.playerapp.ui.events.EventsPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class EventsModule {

    @Binds
    abstract fun bindsEventsPresenter(eventsPresenter: EventsPresenter): EventsContract.Presenter
}