package com.dazn.playerapp.domain

import com.dazn.playerapp.model.Event
import com.dazn.playerapp.model.ScheduleItem
import javax.inject.Inject

class DomainMapper @Inject constructor() {

    fun mapEventsItemToDomain(item: Event): Event {
        return with(item) {
            Event(
                title = title,
                subtitle = subtitle,
                date = date,
                id = id,
                imageUrl = imageUrl,
                videoUrl = videoUrl
            )
        }
    }

    fun mapScheduleItemItemToDomain(item: ScheduleItem): ScheduleItem {
        return with(item) {
            ScheduleItem(
                title = title,
                subtitle = subtitle,
                date = date,
                id = id,
                imageUrl = imageUrl
            )
        }
    }
}