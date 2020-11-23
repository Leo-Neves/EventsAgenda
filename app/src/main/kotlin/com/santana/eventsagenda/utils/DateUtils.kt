package com.santana.eventsagenda.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toTimeString(): String{
    val formatter =  SimpleDateFormat ("yyyy.MM.dd 'at' hh:mm:ss a zzz", Locale.UK)
    return formatter.format(this)
}

fun Date.toDayMonthYear(): String{
    val formatter =  SimpleDateFormat ("dd/MM/yyyy", Locale.UK)
    return formatter.format(this)
}