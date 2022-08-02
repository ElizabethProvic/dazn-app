package com.dazn.playerapp.schedule.ui

import com.dazn.playerapp.model.ScheduleItem

interface ScheduleContract {

    interface View {
        fun setItems(items: List<ScheduleItem>)
        fun hideLoadingView()
        fun showErrorMessage()
    }

    interface Presenter  {
        fun getData(view: View)
        fun clearData()
    }
}