package com.dazn.playerapp.model

import java.util.*

data class Event(
    val date: Date,
    val id: String,
    val imageUrl: String,
    val subtitle: String,
    val title: String,
    val videoUrl: String?
)
