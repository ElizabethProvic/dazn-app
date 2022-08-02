package com.dazn.playerapp.di

import com.dazn.playerapp.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}