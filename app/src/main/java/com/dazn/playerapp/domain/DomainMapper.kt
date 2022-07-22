package com.dazn.playerapp.events.domain

import com.dazn.playerapp.model.Event

class DomainMapper {

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

    fun mapScheduleItemItemToDomain(item: Event): Event {
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
}