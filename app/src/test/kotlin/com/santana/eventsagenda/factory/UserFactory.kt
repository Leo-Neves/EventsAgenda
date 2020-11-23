package com.santana.eventsagenda.factory

import com.santana.eventsagenda.data.model.EventDTO
import com.santana.eventsagenda.factory.RankFactory.mockRankUser

object UserFactory {

    fun mockEvent() =
        EventDTO(
            title = "jon",
            description = "Jon",
            image = 1,
            longitude = "Snow",
            date = 1,
            people = arrayListOf("leadership"),
            price = mockTotalChallenges(),
            latitude = mockRankUser()
        )


}