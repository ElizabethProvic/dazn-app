package com.dazn.playerapp.events.ui

import com.dazn.playerapp.api.PlayerService
import com.dazn.playerapp.di.DaggerApiComponent
import com.dazn.playerapp.events.domain.GetDataUseCase
import com.dazn.playerapp.schedule.ui.ScheduleContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class EventsPresenter @Inject constructor(
    private val getDataUseCase: GetDataUseCase
) : EventsContract.Presenter {

    private val disposable = CompositeDisposable()
    private var _view: EventsContract.View? = null
    private val view get() = _view!!

    override fun getData(view: EventsContract.View) {
        _view = view
        getDataUseCase.getEventsData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { data ->
                    val eventsData = data.sortedBy { it.date }
                    view.hideLoadingView()
                    view.setItems(eventsData)
                },
                onError = { error ->
                    view.hideLoadingView()
                    view.showErrorMessage()
                }
            ).addTo(disposable)
    }

    override fun clearData() {
        disposable.clear()
        _view = null
    }
}