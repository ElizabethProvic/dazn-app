package com.dazn.playerapp.events.ui

import com.dazn.playerapp.model.Event
import com.dazn.playerapp.schedule.ui.ScheduleContract

interface EventsContract {

    interface View {
        fun setItems(items: List<Event>)
        fun hideLoadingView()
        fun showErrorMessage()
    }

    interface Presenter {
        fun getData(view: View)
        fun clearData()
    }
}