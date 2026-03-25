package com.univ.doraboda_compose.util

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Calendar

class CalendarUtil {
    private lateinit var calendar: Calendar
    private var startNum = 0
    private var endNum = 0
    fun setCalendar(cal: Calendar){
        calendar = cal
    }
    fun getDays(labelColor: Int){
    }
}