package com.santana.eventsagenda.data.mapper

import com.santana.eventsagenda.data.model.EventDTO
import com.santana.eventsagenda.domain.model.EventBO
import java.util.*

fun EventDTO.toBO(): EventBO =
    EventBO(
        title = title,
        description = description,
        imageUrl = image,
        latitude = latitude,
        longitude = longitude,
        price = price,
        date = Date(date),
        people = people,
        id = id
    )