package com.santana.eventsagenda.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
class EventBO(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val people: List<String>?,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val price: Double,
    val date: Date
): Parcelable

