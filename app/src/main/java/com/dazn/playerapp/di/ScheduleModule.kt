package com.dazn.playerapp.di

import com.dazn.playerapp.schedule.ui.ScheduleContract
import com.dazn.playerapp.schedule.ui.SchedulePresenter
import dagger.Binds
import dagger.Module

@Module
abstract class ScheduleModule {

    @Binds
    abstract fun bindsSchedulePresenter(schedulePresenter: SchedulePresenter): ScheduleContract.Presenter
}