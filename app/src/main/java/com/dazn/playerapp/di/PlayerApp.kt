package com.dazn.playerapp.di

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class PlayerApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerPlayerAppComponent.factory().create(this)
}
