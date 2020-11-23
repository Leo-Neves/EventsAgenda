package com.santana.eventsagenda.domain.usecase

import com.santana.eventsagenda.data.model.CheckinResponseDTO
import com.santana.eventsagenda.domain.repository.CodewarsRepository
import io.reactivex.Single

class CheckinUseCase(
    val repository: CodewarsRepository
) {

    fun execute(params: Params): Single<CheckinResponseDTO> {
        return repository.checkinEvent(
            params.eventId,
            params.userEmail,
            params.userName
        )
    }

    data class Params(val eventId: String, val userEmail: String, val userName: String)
}