package com.santana.eventsagenda.domain.usecase

import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.domain.repository.CodewarsRepository
import io.reactivex.Single

class FetchEventDetailsUseCase(
    val repository: CodewarsRepository
) {

    fun execute(params: Params): Single<EventBO> {
        return repository.getEventInfo(params.eventId)
    }

    data class Params(val eventId: String)
}