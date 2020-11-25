package com.santana.eventsagenda.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.santana.eventsagenda.domain.EventsViewModelRobot.arrange
import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.domain.usecase.FetchEventsUseCase
import com.santana.eventsagenda.state.EventResponse
import com.santana.eventsagenda.ui.eventlist.EventsViewModel
import io.mockk.mockk
import io.reactivex.Scheduler
import org.junit.Rule
import org.junit.Test

class EventsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val eventsUseCase = mockk<FetchEventsUseCase>()
    private val scheduler = mockk<Scheduler>()
    private val observer = mockk<Observer<EventResponse<List<EventBO>>>>()
    private val viewModel = EventsViewModel(eventsUseCase, scheduler)

    @Test
    fun `when fetch events should return events list`() {
        arrange {
            fetchEventsSuccess(eventsUseCase)
        } act {
            observeEvents(viewModel, observer)
        } assert {
            successFetchEvents(observer)
        }
    }

    @Test
    fun `when fetch events should return error`() {
        arrange {
            fetchEventsError(eventsUseCase)
        } act {
            observeEvents(viewModel, observer)
        } assert {
            failedFetchEvents(observer)
        }
    }

}