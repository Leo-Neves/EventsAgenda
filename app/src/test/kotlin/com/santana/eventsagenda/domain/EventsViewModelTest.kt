package com.santana.eventsagenda.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.santana.eventsagenda.domain.UserViewModelRobot.arrange
import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.domain.usecase.CheckinUseCase
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
    private val usersUseCase = mockk<CheckinUseCase>()
    private val listUseCase = mockk<FetchEventsUseCase>()
    private val scheduler = mockk<Scheduler>()
    private val observer = mockk<Observer<EventResponse<List<EventBO>>>>()
    private val viewModel = EventsViewModel(usersUseCase, listUseCase, scheduler)

    @Test
    fun `when fetch users should return user list`() {
        arrange {
            fetchUsersSuccess(usersUseCase)
        } act {
            observeUsers(viewModel, observer)
        } assert {
            successFetchUsers(observer)
        }
    }

    @Test
    fun `when fetch users should return error`() {
        arrange {
            fetchUsersError(usersUseCase)
        } act {
            observeUsers(viewModel, observer)
        } assert {
            failedFetchUsers(observer)
        }
    }

}