package com.santana.eventsagenda.domain

import androidx.lifecycle.Observer
import com.santana.eventsagenda.data.mapper.toBO
import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.domain.usecase.FetchEventsUseCase
import com.santana.eventsagenda.factory.EventFactory.mockEvents
import com.santana.eventsagenda.state.EventResponse
import com.santana.eventsagenda.ui.eventlist.EventsViewModel
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import io.reactivex.Single
import okhttp3.internal.EMPTY_RESPONSE
import org.junit.Assert.assertTrue
import retrofit2.HttpException
import retrofit2.Response

object EventsViewModelRobot {

    class EventsViewModelArrange {

        fun fetchEventsSuccess(
            eventsUseCase: FetchEventsUseCase
        ) {
            every { eventsUseCase.execute() } returns Single.just(mockEvents())
                .map { list ->
                    list.map { event -> event.toBO() }
                }
        }

        fun fetchEventsError(
            eventsUseCase: FetchEventsUseCase
        ) {
            every { eventsUseCase.execute() } returns Single.error(
                HttpException(Response.error<String>(404, EMPTY_RESPONSE))
            )
        }

        infix fun act(func: EventsViewModelAct.() -> Unit) =
            EventsViewModelAct().apply(func)
    }

    class EventsViewModelAct {

        fun observeEvents(
            viewModel: EventsViewModel,
            observer: Observer<EventResponse<List<EventBO>>>
        ) = viewModel.eventsLiveData.observeForever(observer)

        infix fun assert(func: EventsViewModelAssert.() -> Unit) =
            EventsViewModelAssert().apply(func)
    }

    class EventsViewModelAssert {

        fun successFetchEvents(observer: Observer<EventResponse<List<EventBO>>>) {
            val slot = slot<EventResponse<List<EventBO>>>()
            verify { observer.onChanged(capture(slot)) }
            assertTrue(slot.captured is EventResponse.EventSuccess)
        }

        fun failedFetchEvents(observer: Observer<EventResponse<List<EventBO>>>) {
            val slot = slot<EventResponse<List<EventBO>>>()
            verify { observer.onChanged(capture(slot)) }
            assertTrue(slot.captured is EventResponse.NetworkError)
        }

    }

    infix fun arrange(func: EventsViewModelArrange.() -> Unit) =
        EventsViewModelArrange().apply(func)
}