package com.dazn.playerapp.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProviderImpl : SchedulerProvider {

    override val io = Schedulers.io()

    override val ui: Scheduler = AndroidSchedulers.mainThread()

    override val timer = Schedulers.computation()
}
