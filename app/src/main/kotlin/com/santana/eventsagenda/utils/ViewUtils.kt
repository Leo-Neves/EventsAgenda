package com.santana.eventsagenda.utils

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.setVisibility(isVisible: Boolean){
    visibility = if (isVisible) VISIBLE else GONE
}