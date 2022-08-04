package com.dazn.playerapp.ui.events

import com.dazn.playerapp.domain.GetDataUseCase
import com.dazn.playerapp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class EventsPresenter @Inject constructor(
    private val scheduler: SchedulerProvider,
    private val getDataUseCase: GetDataUseCase
) : EventsContract.Presenter {

    private val disposable = CompositeDisposable()
    private var _view: EventsContract.View? = null
    private val view get() = _view!!

    override fun getData(view: EventsContract.View) {
        _view = view
        getDataUseCase.getEventsData()
            .observeOn(scheduler.ui)
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