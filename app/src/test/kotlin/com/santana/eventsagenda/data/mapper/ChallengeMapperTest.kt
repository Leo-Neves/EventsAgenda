package com.santana.eventsagenda.data.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.santana.eventsagenda.factory.ChallengeFactory.mockChallengeAuthored
import com.santana.eventsagenda.factory.ChallengeFactory.mockChallengeCompleted
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ChallengeMapperTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `when map ChallengeAuthoredDTO should return BO`(){
        val mapped = mockChallengeAuthored().toBO()
        assertEquals(1, mapped.data.size)
        assertEquals("id", mapped.data[0].id)
        assertEquals("binary search in cpp", mapped.data[0].description)
        assertEquals("binary search", mapped.data[0].name)
        assertEquals(2, mapped.data[0].rank)
        assertEquals("3 yut", mapped.data[0].rankName)
        assertEquals(1, mapped.data[0].languages.size)
        assertEquals(2, mapped.data[0].tags.size)
    }

    @Test
    fun `when map ChallengeCompletedDTO should return BO`(){
        val mapped = mockChallengeCompleted().toBO()
        assertEquals(1, mapped.data.size)
        assertEquals("id", mapped.data[0].id)
        assertEquals("airport way", mapped.data[0].name)
        assertEquals("fastest way to go to airport", mapped.data[0].slug)
        assertEquals(1, mapped.data[0].completedLanguages.size)
        assertEquals("fortran", mapped.data[0].completedLanguages[0])
    }

}