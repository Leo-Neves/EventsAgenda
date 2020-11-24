package com.santana.eventsagenda.domain.repository

import com.santana.eventsagenda.data.model.CheckinResponseDTO
import com.santana.eventsagenda.domain.model.CheckinBO
import com.santana.eventsagenda.domain.model.EventBO
import io.reactivex.Single

interface CodewarsRepository {
    fun getEventInfo(eventId: String): Single<EventBO>
    fun getEvents(): Single<List<EventBO>>
    fun checkinEvent(id: String, email: String, name: String): Single<CheckinBO>
}