package com.dazn.playerapp.ui.schedule

import com.dazn.playerapp.domain.GetDataUseCase
import com.dazn.playerapp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SchedulePresenter @Inject constructor(
    private val scheduler: SchedulerProvider,
    private val getDataUseCase: GetDataUseCase
) : ScheduleContract.Presenter {

    private val disposable = CompositeDisposable()
    private var _view: ScheduleContract.View? = null
    private val view get() = _view!!

    override fun getData(view: ScheduleContract.View) {
        _view = view
        getDataUseCase.getScheduleData()
            .observeOn(scheduler.ui)
            .repeatWhen { completed -> completed.delay(30, TimeUnit.SECONDS, scheduler.timer) }
            .subscribe(
                { data ->
                    val eventsData = data.sortedBy { it.date }
                    view.hideLoadingView()
                    view.setItems(eventsData)
                },
                {
                    view.hideLoadingView()
                    view.showErrorMessage()
                }).addTo(disposable)
    }

    override fun clearData() {
        disposable.clear()
        _view = null
    }
}