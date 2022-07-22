package com.dazn.playerapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dazn.playerapp.api.PlayerService
import com.dazn.playerapp.model.Event
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class EventsViewModelTest {

//    @get:Rule
//    var rule = InstantTaskExecutorRule()
//
//    @Mock
//    lateinit var playerService: PlayerService
//
//    private var testSingle: Single<ArrayList<Event>>? = null
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//    }
//
//    @Test
//    fun getEventsSuccess() {
//        val event = Event(Date("2022-06-26T01:28:10.016Z"), "1", "", "subtitle", "title", "")
//        val eventList = arrayListOf(event)
//
//        testSingle = Single.just(eventList)
//
//        Mockito.`when`(playerService.getEvents()).thenReturn(testSingle)
//    }
//
//    @Before
//    fun setUpRxSchedulers() {
//        val immediate = object : Scheduler() {
//            override fun createWorker(): Worker {
//                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
//            }
//
//            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
//                return super.scheduleDirect(run, 0, unit)
//            }
//        }
//
//        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
//        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
//        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
//        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
//        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
//    }
}