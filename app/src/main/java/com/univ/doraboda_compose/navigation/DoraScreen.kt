package com.univ.doraboda_compose.navigation

import kotlinx.serialization.Serializable
import java.time.LocalDate

sealed class DoraScreen {
    @Serializable
    object Main : DoraScreen()
    @Serializable
    data class DailyDetail(val year: Int, val month: Int, val day: Int, val thisEmotion: String?) : DoraScreen()
    @Serializable
    object DailyWrite : DoraScreen()
    @Serializable
    object Settings : DoraScreen()

    @Serializable
    object DoraCalendar : DoraScreen()

    @Serializable
    object DoraMusicPlayer : DoraScreen()

    @Serializable
    object DoraData : DoraScreen()
}