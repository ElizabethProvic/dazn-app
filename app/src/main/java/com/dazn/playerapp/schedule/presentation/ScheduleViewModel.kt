package com.dazn.playerapp.schedule.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dazn.playerapp.api.PlayerService
import com.dazn.playerapp.di.DaggerApiComponent
import com.dazn.playerapp.extenstions.ViewData
import com.dazn.playerapp.extenstions.isDateTomorrow
import com.dazn.playerapp.model.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ScheduleViewModel : ViewModel() {

    @Inject
    lateinit var playerService: PlayerService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    private val _state = MutableLiveData<ViewData<List<Event>>>()

    val state: LiveData<ViewData<List<Event>>> = _state

    fun start() {
        _state.value = ViewData.Loading()

        disposable.add(
            playerService.getSchedule()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Event>>() {
                    override fun onSuccess(value: List<Event>) {
                        val scheduleData = value.sortedBy { it.date }.filter { isDateTomorrow(it.date.time) }
                        _state.value = ViewData.Data(scheduleData)
                    }

                    override fun onError(e: Throwable) {
                        _state.value = ViewData.Error(e)
                    }
                })
        )
    }

    fun refresh() {
        _state.value = ViewData.Refreshing()
        start()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}