package br.com.monitordenoticias.config

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

enum class SearchMethod { NEWS, DEMANDS, VIDEOS }
enum class ScheduleMode { INTERVAL, FIXED_TIMES }

data class SearchSchedule(
    val enabled: Boolean = true,
    val mode: ScheduleMode = ScheduleMode.INTERVAL,
    val intervalMinutes: Int = 30,
    val fixedTimes: List<LocalTime> = emptyList(),
) {
    init {
        require(intervalMinutes >= 1) { "intervalMinutes deve ser >= 1" }
    }

    fun normalized(): SearchSchedule = copy(fixedTimes = fixedTimes.distinct().sorted())

    fun isDue(now: LocalDateTime, lastRun: LocalDateTime?): Boolean {
        if (!enabled) return false
        if (lastRun == null) return true
        return when (mode) {
            ScheduleMode.INTERVAL -> ChronoUnit.MINUTES.between(lastRun, now) >= intervalMinutes
            ScheduleMode.FIXED_TIMES -> fixedTimes.any { scheduled ->
                val scheduledAt = now.toLocalDate().atTime(scheduled)
                !scheduledAt.isAfter(now) && lastRun.isBefore(scheduledAt)
            }
        }
    }

    companion object {
        fun parseTimes(text: String): List<LocalTime> = text
            .split(',', ';', '\n')
            .map(String::trim)
            .filter(String::isNotEmpty)
            .map(LocalTime::parse)
            .distinct()
            .sorted()
    }
}

data class AutomationSettings(
    val news: SearchSchedule = SearchSchedule(intervalMinutes = 30),
    val demands: SearchSchedule = SearchSchedule(intervalMinutes = 60),
    val videos: SearchSchedule = SearchSchedule(
        mode = ScheduleMode.FIXED_TIMES,
        fixedTimes = listOf("08:00", "12:00", "15:00", "19:00", "21:00").map(LocalTime::parse),
    ),
) {
    fun forMethod(method: SearchMethod): SearchSchedule = when (method) {
        SearchMethod.NEWS -> news
        SearchMethod.DEMANDS -> demands
        SearchMethod.VIDEOS -> videos
    }
}
