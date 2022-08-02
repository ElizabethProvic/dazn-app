@file:Suppress("SpellCheckingInspection")

package com.dazn.playerapp.extenstions

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import java.util.concurrent.TimeUnit

private const val DATE_FORMAT: String = "EEEE d MMMM y"
private const val TIME_FORMAT: String = "HH:mm"

fun Date?.eventDateFormatToString(): String {
    val format = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return format.format(this)
}

fun Date?.eventTimeFormatToString(): String {
    val format = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
    return format.format(this)
}

fun isDateTomorrow(epochMilli: Long): Boolean {
    val tomorrow = LocalDate.now().plusDays(1)
    val chosenDay = LocalDate.ofEpochDay(epochMilli / TimeUnit.DAYS.toMillis(1))
    return tomorrow == chosenDay
}