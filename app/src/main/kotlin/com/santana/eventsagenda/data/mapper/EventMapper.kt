package com.santana.eventsagenda.data.mapper

import com.santana.eventsagenda.data.model.EventDTO
import com.santana.eventsagenda.domain.model.EventBO
import java.util.Date

fun EventDTO.toBO(): EventBO =
    EventBO(
        title = title,
        description = description,
        imageUrl = image,
        latitude = latitude,
        longitude = longitude,
        people = null,
        price = price,
        date = Date(date),
        id = id
    )