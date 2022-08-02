package com.dazn.playerapp.di

import com.dazn.playerapp.util.SchedulerProvider
import com.dazn.playerapp.util.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulersModel {

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()
}