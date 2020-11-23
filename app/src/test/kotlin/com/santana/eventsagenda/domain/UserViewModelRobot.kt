package com.santana.eventsagenda.domain

import androidx.lifecycle.Observer
import com.santana.eventsagenda.data.mapper.toBO
import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.domain.usecase.CheckinUseCase
import com.santana.eventsagenda.factory.UserFactory.mockEvent
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

object UserViewModelRobot {

    class UserViewModelArrange {

        fun fetchUsersSuccess(
            usersUseCase: CheckinUseCase
        ) {
            every { usersUseCase.execute(any()) } returns Single.just(
                listOf(mockEvent().toBO())
            )
        }

        fun fetchUsersError(
            usersUseCase: CheckinUseCase
        ) {
            every { usersUseCase.execute(any()) } returns Single.error(
                HttpException(Response.error<String>(404, EMPTY_RESPONSE))
            )
        }

        infix fun act(func: UserViewModelAct.() -> Unit) =
            UserViewModelAct().apply(func)
    }

    class UserViewModelAct {

        fun observeUsers(
            viewModel: EventsViewModel,
            observer: Observer<EventResponse<List<EventBO>>>
        ) = viewModel.eventsLiveData.observeForever(observer)

        infix fun assert(func: UserViewModelAssert.() -> Unit) =
            UserViewModelAssert().apply(func)
    }

    class UserViewModelAssert {

        fun successFetchUsers(observer: Observer<EventResponse<List<EventBO>>>){
            val slot = slot<EventResponse<List<EventBO>>>()
            verify { observer.onChanged(capture(slot)) }
            assertTrue(slot.captured is EventResponse.EventSuccess)
        }

        fun failedFetchUsers(observer: Observer<EventResponse<List<EventBO>>>){
            val slot = slot<EventResponse<List<EventBO>>>()
            verify { observer.onChanged(capture(slot)) }
            assertTrue(slot.captured is EventResponse.EventNotFound)
        }

    }

    infix fun arrange(func: UserViewModelArrange.() -> Unit) =
        UserViewModelArrange().apply(func)
}