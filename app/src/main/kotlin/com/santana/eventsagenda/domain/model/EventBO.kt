package com.santana.eventsagenda.domain.model

import java.util.Date

data class EventBO(
    val id: String,
    val title: String,
    val description: String,
    val people: List<PersonBO>?,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val price: Double,
    val date: Date
)

