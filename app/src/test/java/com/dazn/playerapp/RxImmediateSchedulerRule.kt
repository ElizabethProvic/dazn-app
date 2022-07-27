package com.dazn.playerapp

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import java.util.concurrent.TimeUnit
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RxImmediateSchedulerRule : TestRule {

    private val immediate = object : Scheduler() {
        override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
            return super.scheduleDirect(run, 0, unit)
        }
        override fun createWorker(): Worker {
            return ExecutorScheduler.ExecutorWorker(Runnable::run, false)
        }
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setInitIoSchedulerHandler { immediate }
                RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
                RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
                RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}
/**
 * Inside prod app we don' use rule to override rx schedulers
 * Instead we prefer to inject schedulers and use different implementation during test and in regular app
 * It will be Schedulers.trampoline() inside unit test
 *
 * Refactored presenter (also with constructor injection and view passed as a function argument
 *
 * class SchedulePresenter @Inject constructor(
 *     private val mainThreadScheduler: Scheduler,
 *     private val getDataUseCase: GetDataUseCase
 * ) : ScheduleContract.Presenter {
 *
 *     private val disposable = CompositeDisposable()
 *     private var _view: ScheduleContract.View? = null
 *     private val view get() = _view!!
 *
 *     override fun getData(view: ScheduleContract.View) {
 *         _view = view
 *         getDataUseCase.getScheduleData()
 *             .observeOn(mainThreadScheduler)
 *             .subscribeBy(
 *                 onSuccess = { data ->
 *                     val eventsData = data.sortedBy { it.date }
 *                     view.hideLoadingView()
 *                     view.setItems(eventsData)
 *                 },
 *                 onError = { error ->
 *                     view.hideLoadingView()
 *                     view.showErrorMessage()
 *                 }
 *             ).addTo(disposable)
 *     }
 *
 *     override fun clearData() {
 *         disposable.clear()
 *         _view = null
 *     }
 * }
 * */