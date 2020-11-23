package com.santana.eventsagenda.domain.usecase

import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.domain.repository.CodewarsRepository
import io.reactivex.Single

class FetchEventsUseCase(
    val repository: CodewarsRepository
) {

    fun execute(): Single<List<EventBO>> {
        return repository.getEvents()
    }

}