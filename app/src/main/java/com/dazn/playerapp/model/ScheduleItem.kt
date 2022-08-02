package com.dazn.playerapp.model

import java.util.*

data class ScheduleItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: Date,
    val imageUrl: String,
)
