package com.dazn.playerapp.di

import com.dazn.playerapp.ui.schedule.ScheduleContract
import com.dazn.playerapp.ui.schedule.SchedulePresenter
import dagger.Binds
import dagger.Module

@Module
abstract class ScheduleModule {

    @Binds
    abstract fun bindsSchedulePresenter(schedulePresenter: SchedulePresenter): ScheduleContract.Presenter
}