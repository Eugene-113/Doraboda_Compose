package com.univ.calendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.univ.calendar.ui.component.CalendarDialog
import com.univ.doraboda_compose.ui.calendar.CalendarTopBar
import com.univ.ui.theme.Dora_ComposeTheme
import com.univ.ui.theme.White100
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun CalendarScreen(
    currentDate: LocalDate = LocalDate.now(),
    minDate: LocalDate = LocalDate.of(2000, 1, 1),
    maxDate: LocalDate = LocalDate.of(2999, 12, 1),
){
    val scope = rememberCoroutineScope()
    var dialogState by remember { mutableStateOf(false) }
    var dateState by remember { mutableStateOf<LocalDate>(LocalDate.of(2000, 1, 1)) }
    val pagerState = rememberPagerState(
    pageCount = { ChronoUnit.MONTHS.between(minDate, maxDate).toInt() + 1 },
    initialPage = (ChronoUnit.MONTHS.between(minDate, currentDate).toInt())
    )
    LaunchedEffect(pagerState.currentPage) {
        dateState = minDate.plusMonths(pagerState.currentPage.toLong())
    }
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = White100)
    ) {
        CalendarTopBar(dateState){
            dialogState = true
        }
        CalendarListView(
            currentDate = currentDate,
            minDate = minDate,
            maxDate = maxDate,
            pagerState = pagerState
        )
    }
    if(dialogState){
        CalendarDialog(
            onClickDone = { date ->
                val index = ChronoUnit.MONTHS.between(minDate, date).toInt()
                scope.launch {
                    pagerState.animateScrollToPage(page = index)
                }
                dialogState = false
                          },
            onClickDismiss = { dialogState = false },
            defaultLocalDate = dateState
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