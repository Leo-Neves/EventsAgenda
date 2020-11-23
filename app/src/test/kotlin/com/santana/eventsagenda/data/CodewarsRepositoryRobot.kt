package com.santana.eventsagenda.data

import android.accounts.NetworkErrorException
import com.santana.eventsagenda.data.dao.UserDao
import com.santana.eventsagenda.data.repository.CodewarsRepositoryImpl
import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.factory.UserFactory.mockEvent
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single

object CodewarsRepositoryRobot {
    private val api = mockk<CodewarsApi>()
    private val userDao = mockk<UserDao>()
    private val subject = CodewarsRepositoryImpl(
        api = api,
        userDao = userDao
    )

    class CodewarsRepositoryArrange{

        fun mockUserFromApi(){
            every {
                api.events(any())
            } returns Single.just(mockEvent())
        }

        fun mockNetworkError(){
            every {
                api.events(any())
            } returns Single.error(NetworkErrorException())
        }

        infix fun act(func: CodewarsRepositoryAct.() -> Unit) =
            CodewarsRepositoryAct().apply(func)
    }

    class CodewarsRepositoryAct{

        fun getUsers(): Single<EventBO> {
            return subject.getEventInfo("jon")
        }

        infix fun assert(func: CodewarsRepositoryAssert.() -> Unit){
            CodewarsRepositoryAssert().apply(func)
        }
    }

    class CodewarsRepositoryAssert{

        fun success(observer: Single<EventBO>){
            observer.test()
                .assertComplete()
                .assertValue { it.id.size == 3 }
                .assertValue { it.title.score == 2000 }
        }

        fun error(observer: Single<EventBO>){
            observer.test()
                .assertError(NetworkErrorException::class.java)
        }

    }

    infix fun arrange(func: CodewarsRepositoryArrange.() -> Unit) =
        CodewarsRepositoryArrange().apply(func)
}