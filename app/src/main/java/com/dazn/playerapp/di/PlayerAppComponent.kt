package com.dazn.playerapp.di
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApiModule::class,
        ActivityModule::class,
        FragmentsModule::class,
        EventsModule::class,
        ScheduleModule::class
    ]
)
interface PlayerAppComponent : AndroidInjector<PlayerApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<PlayerApp>
}