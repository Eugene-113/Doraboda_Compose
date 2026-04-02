package com.univ.calendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.univ.doraboda_compose.theme.Dora_ComposeTheme
import com.univ.doraboda_compose.theme.MondayRed
import com.univ.doraboda_compose.theme.TodayYellow
import com.univ.doraboda_compose.theme.WeekBlack
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.Locale

@Composable
fun CalendarListView(
    currentDate: LocalDate = LocalDate.now(),
    minDate: LocalDate = LocalDate.of(2000, 1, 1),
    maxDate: LocalDate = LocalDate.of(2999, 12, 1),
    pagerState: PagerState = rememberPagerState(
        pageCount = { ChronoUnit.MONTHS.between(minDate, maxDate).toInt() + 1 },
        initialPage = (ChronoUnit.MONTHS.between(minDate, currentDate).toInt())
    )
){
    Column{
        WeekView()
        HorizontalPager(state = pagerState,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            verticalAlignment = Alignment.Top
        ) { page ->
            val thisDate = minDate.plusMonths(page.toLong())
            MonthView(thisDate = thisDate, currentDate)
        }
    }
}

@Composable
fun MonthView(
    thisDate: LocalDate,
    currentDate: LocalDate
){
    val daysNumber = thisDate.lengthOfMonth()
    val firstDay = thisDate.dayOfWeek.value
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier
    ) {
        for(i in 0 until firstDay % 7){
            item {
                Box(
                    modifier = Modifier
                        .height(50.dp)
                )
            }
        }
        items(daysNumber){ index ->
            val present = thisDate.withDayOfMonth(index + 1)
            DayView(day = index + 1, isToday = present.compareTo(currentDate) == 0)
        }
    }
}

@Composable
fun WeekView(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ){
        for(i in 6..12){
            Text(
                text = DayOfWeek.entries[i%7].getDisplayName(TextStyle.SHORT, Locale.ENGLISH).uppercase(),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = if(i == 6) MondayRed else WeekBlack
            )
        }
    }
}

@Composable
fun DayView(day: Int, isToday: Boolean){
    Column(
        modifier = Modifier
            .height(100.dp)
            .background(color = (if(isToday) TodayYellow else Color.Transparent))
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = day.toString(),
            modifier = Modifier,
            fontSize = 25.sp
            )
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarListPreview() {
    Dora_ComposeTheme{
        CalendarListView()
    }
}