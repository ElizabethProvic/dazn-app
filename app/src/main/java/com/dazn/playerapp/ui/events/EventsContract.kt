package com.dazn.playerapp.ui.events

import com.dazn.playerapp.model.Event

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