package com.santana.eventsagenda.data.repository

import com.santana.eventsagenda.data.CodewarsApi
import com.santana.eventsagenda.data.mapper.toBO
import com.santana.eventsagenda.data.model.CheckinRequestDTO
import com.santana.eventsagenda.data.model.CheckinResponseDTO
import com.santana.eventsagenda.domain.model.CheckinBO
import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.domain.repository.CodewarsRepository
import io.reactivex.Single

class CodewarsRepositoryImpl(
    private val api: CodewarsApi
) : CodewarsRepository {

    override fun getEventInfo(eventId: String): Single<EventBO> {
        return api.event(eventId).map { it.toBO() }
    }

    override fun getEvents(): Single<List<EventBO>> {
        return api.events().map { it -> it.map { it.toBO() } }
    }

    override fun checkinEvent(
        id: String,
        email: String,
        name: String
    ): Single<CheckinBO> {
        return api.checkin(CheckinRequestDTO(id, name, email)).map { CheckinBO(id, name, email) }
    }
}