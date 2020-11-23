package com.santana.eventsagenda.ui

import android.app.Activity
import android.content.Intent
import com.santana.eventsagenda.ui.eventdetails.EventDetailsActivity

object EventsRouter {

    fun openEventDatailsActivity(activity: Activity, eventId: String) {
        val intent = Intent(activity, EventDetailsActivity::class.java)
        intent.putExtra(EVENT_SELECTED_ID, eventId)
        activity.startActivityForResult(intent, EVENTS_REFRESH_REQUEST_CODE)
    }

    const val EVENT_SELECTED_ID = "EventSelectedId"
    const val EVENTS_NEEDS_REFRESH = "EventsNeedsRefresh"
    const val EVENTS_REFRESH_REQUEST_CODE = 33
}

