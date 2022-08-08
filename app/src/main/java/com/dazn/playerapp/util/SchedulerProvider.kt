package com.dazn.playerapp.util

import io.reactivex.Scheduler
import java.util.*

interface SchedulerProvider {

    val io: Scheduler

    val ui: Scheduler

    val timer: Scheduler
}
