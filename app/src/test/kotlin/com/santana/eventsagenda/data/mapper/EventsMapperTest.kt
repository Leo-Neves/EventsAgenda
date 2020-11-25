package com.santana.eventsagenda.data.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.santana.eventsagenda.factory.EventFactory.mockEventPoolParty
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class EventsMapperTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `when map EventoDTO should return BO`(){
        val mapped = mockEventPoolParty().toBO()
        assertEquals("42", mapped.id)
        assertEquals("Pool Party", mapped.title)
        assertEquals("https://www.bonde.com.br/img/bondenews/2018/11/img_2842.jpg", mapped.imageUrl)
        assertEquals(49.9, mapped.price)
        assertEquals(-53.4488875, mapped.longitude)
        assertEquals(-33.6923026, mapped.latitude)
        assertEquals(1606262400000, mapped.date.time)
        assertEquals(null, mapped.people)
    }

}