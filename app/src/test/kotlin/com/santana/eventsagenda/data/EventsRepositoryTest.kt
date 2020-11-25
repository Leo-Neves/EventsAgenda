package com.santana.eventsagenda.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.santana.eventsagenda.data.EventsRepositoryRobot.arrange
import com.santana.eventsagenda.domain.model.EventBO
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

class EventsRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `when network is ok return list of events`(){
        lateinit var response: Single<List<EventBO>>
        arrange{
            mockUserFromApi()
        } act {
            response = getEvents()
        } assert {
            success(response)
        }
    }

    @Test
    fun `when network is off return throws an error`(){
        lateinit var single: Single<List<EventBO>>
        arrange{
            mockNetworkError()
        } act {
            single = getEvents()
        } assert {
            errorListingEvents(single)
        }
    }
}