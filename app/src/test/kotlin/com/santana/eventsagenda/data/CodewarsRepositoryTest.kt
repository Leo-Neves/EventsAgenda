package com.santana.eventsagenda.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.santana.eventsagenda.data.CodewarsRepositoryRobot.arrange
import com.santana.eventsagenda.domain.model.EventBO
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

class CodewarsRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `when network is ok return list of users`(){
        lateinit var single: Single<EventBO>
        arrange{
            mockUserFromApi()
        } act {
            single = getUsers()
        } assert {
            success(single)
        }
    }

    @Test
    fun `when network is off return throws an error`(){
        lateinit var single: Single<EventBO>
        arrange{
            mockNetworkError()
        } act {
            single = getUsers()
        } assert {
            error(single)
        }
    }
}