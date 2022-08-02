package com.dazn.playerapp.schedule.ui

import com.dazn.playerapp.events.domain.GetDataUseCase
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SchedulePresenter @Inject constructor(
    private val getDataUseCase: GetDataUseCase
) : ScheduleContract.Presenter {

    private val disposable = CompositeDisposable()
    private var _view: ScheduleContract.View? = null
    private val view get() = _view!!

    override fun getData(view: ScheduleContract.View) {
        _view = view
        getDataUseCase.getScheduleData()
            .observeOn(AndroidSchedulers.mainThread())
            .repeatWhen { completed -> completed.delay(30, TimeUnit.SECONDS) }
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