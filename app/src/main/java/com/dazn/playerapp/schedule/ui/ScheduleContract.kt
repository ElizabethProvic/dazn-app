package com.dazn.playerapp.schedule.ui

import com.dazn.playerapp.model.Event

interface ScheduleContract {

    interface View {
        fun setItems(items: List<Event>)
        fun hideLoadingView()
        fun showErrorMessage()
    }

    interface Presenter  {
        fun getData()
    }
}