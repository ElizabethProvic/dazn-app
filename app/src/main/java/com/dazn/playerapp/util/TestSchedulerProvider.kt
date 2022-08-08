package com.dazn.playerapp.util

import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider : SchedulerProvider {

    override val io = Schedulers.trampoline()

    override val timer = TestScheduler()

    override val ui = Schedulers.trampoline()
}
