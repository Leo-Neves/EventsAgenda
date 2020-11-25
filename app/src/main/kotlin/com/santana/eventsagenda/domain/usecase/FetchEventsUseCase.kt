package com.santana.eventsagenda.domain.usecase

import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.domain.repository.EventsRepository
import io.reactivex.Single

class FetchEventsUseCase(
    val repository: EventsRepository
) {

    fun execute(): Single<List<EventBO>> {
        return repository.getEvents()
    }

}