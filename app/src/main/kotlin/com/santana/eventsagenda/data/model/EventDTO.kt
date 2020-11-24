package com.santana.eventsagenda.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventDTO(
    @SerializedName("date") val date: Long,
    @SerializedName("description") val description: String,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("price") val price: Double,
    @SerializedName("id") val id: String
): Parcelable