package com.univ.doraboda_compose.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.univ.doraboda_compose.theme.Dora_ComposeTheme
import com.univ.doraboda_compose.theme.White100
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun CalendarScreen(
    currentDate: LocalDate = LocalDate.now(),
    minDate: LocalDate = LocalDate.of(2000, 1, 1),
    maxDate: LocalDate = LocalDate.of(2999, 12, 1),
){
    val pagerState = rememberPagerState(
    pageCount = { ChronoUnit.MONTHS.between(minDate, maxDate).toInt() + 1 },
    initialPage = (ChronoUnit.MONTHS.between(minDate, currentDate).toInt())
    )
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = White100)
    ) {
        CalendarTopBar(pagerState = pagerState)
        CalendarListView(
            currentDate = currentDate,
            minDate = minDate,
            maxDate = maxDate,
            pagerState = pagerState
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    Dora_ComposeTheme{
        CalendarScreen()
    }
}