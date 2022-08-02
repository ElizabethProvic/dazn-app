package com.dazn.playerapp.di

import com.dazn.playerapp.events.ui.EventsFragment
import com.dazn.playerapp.schedule.ui.ScheduleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun bindEventsFragment(): EventsFragment

    @ContributesAndroidInjector
    abstract fun contributesScheduleFragment(): ScheduleFragment
}