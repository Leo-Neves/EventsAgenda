package com.santana.eventsagenda.data

import com.santana.eventsagenda.data.model.*
import io.reactivex.Single
import retrofit2.http.*

interface CodewarsApi {
    @GET("api/events/{eventId}")
    fun event(@Path("eventId") eventId: String): Single<EventDTO>

    @GET("api/events")
    fun events(): Single<List<EventDTO>>

    @POST("api/checkin")
    fun checkin(@Body checkinRequest: CheckinRequestDTO): Single<CheckinResponseDTO>
}