package com.dazn.playerapp.util

import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulerProvider {

    override val io = Schedulers.trampoline()

    override val computation = Schedulers.trampoline()

    override val ui = Schedulers.trampoline()
}
