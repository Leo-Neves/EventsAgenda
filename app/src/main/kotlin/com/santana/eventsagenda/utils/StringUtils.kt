package com.santana.eventsagenda.utils

import android.content.Context

fun List<String>.splitStringList(
    context: Context,
    emptyListString: Int,
    fullListString: Int
): String {
    return if (isEmpty()) {
        context.getString(emptyListString)
    } else {
        context.getString(
            fullListString,
            joinToString { it.capitalize() }
        )
    }
}