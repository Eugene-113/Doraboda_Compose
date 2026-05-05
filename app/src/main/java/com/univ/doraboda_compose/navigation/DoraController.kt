package com.univ.doraboda_compose.navigation

import androidx.navigation.NavHostController
import java.time.LocalDate

class DoraController(val navController: NavHostController) {
    fun navigateToDailyDetail(date: LocalDate, emotion: String?){
        navController.navigate(
            DoraScreen.DailyDetail(
                year = date.year, month = date.monthValue,
                date.dayOfMonth, emotion))
    }
}