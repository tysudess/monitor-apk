package br.com.monitordenoticias.config

import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ConfigSmokeTest {
    @Test fun defaultTermsContain18Entries() {
        assertEquals(18, DefaultTerms.NEWS.size)
        assertTrue("MINISTÉRIO DA DEFESA" in DefaultTerms.NEWS)
    }

    @Test fun intervalScheduleWorks() {
        val schedule = SearchSchedule(intervalMinutes = 30)
        val last = LocalDateTime.of(2026, 9, 9, 10, 0)
        assertFalse(schedule.isDue(last.plusMinutes(29), last))
        assertTrue(schedule.isDue(last.plusMinutes(30), last))
    }

    @Test fun fixedTimesWork() {
        val schedule = SearchSchedule(mode = ScheduleMode.FIXED_TIMES, fixedTimes = listOf(LocalTime.of(12, 0)))
        val last = LocalDateTime.of(2026, 9, 9, 11, 50)
        assertTrue(schedule.isDue(LocalDateTime.of(2026, 9, 9, 12, 1), last))
    }
}
