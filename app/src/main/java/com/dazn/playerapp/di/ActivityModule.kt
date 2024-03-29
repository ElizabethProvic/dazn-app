package com.dazn.playerapp.di

import com.dazn.playerapp.ui.events.EventsFragment
import com.dazn.playerapp.ui.schedule.ScheduleFragment
import com.dazn.playerapp.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    abstract fun contributesMainActivity(): MainActivity

    @Module
    abstract class FragmentsModule {

        @ContributesAndroidInjector
        abstract fun bindEventsFragment(): EventsFragment

        @ContributesAndroidInjector
        abstract fun contributesScheduleFragment(): ScheduleFragment
    }
}