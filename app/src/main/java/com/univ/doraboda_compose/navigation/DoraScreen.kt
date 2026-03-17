package com.univ.doraboda_compose.navigation

import kotlinx.serialization.Serializable

sealed class DoraScreen {
    @Serializable
    object Main : DoraScreen()
    @Serializable
    object DailyDetail : DoraScreen()
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