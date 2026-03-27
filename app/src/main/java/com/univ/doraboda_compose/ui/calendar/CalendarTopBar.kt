package com.univ.doraboda_compose.ui.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.univ.doraboda_compose.R
import com.univ.doraboda_compose.theme.Dora_ComposeTheme
import com.univ.doraboda_compose.theme.TodayYellow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarTopBar(
    pagerState: PagerState,
    minDate: LocalDate = LocalDate.of(2000, 1, 1)
){
    val format = DateTimeFormatter.ofPattern("yyyy년 MM월")
    var thisDateString by remember { mutableStateOf("") }
    LaunchedEffect(pagerState.currentPage) {
        val date = minDate.plusMonths(pagerState.currentPage.toLong())
        thisDateString = date.format(format)
    }
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        windowInsets = WindowInsets(0, 0, 0, 0),
        title = {
            ConstraintLayout(modifier =
                Modifier.fillMaxWidth()
            ){
                val (arrow, text, gear) = createRefs()
                Image(
                    painter = painterResource(R.drawable.icon_arrow),
                    modifier = Modifier.size(50.dp)
                        .constrainAs(text){
                            end.linkTo(arrow.start)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(parent.top)
                        }
                    ,
                    contentDescription = null
                )
                Text(
                    text = thisDateString,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .constrainAs(arrow){
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(parent.top)
                        }
                )
                Image(
                    painter = painterResource(R.drawable.icon_setting),
                    modifier = Modifier
                        .constrainAs(gear){
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(parent.top)
                        }
                        .padding(end = 10.dp)
                        .size(40.dp)
                    ,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BarPreview() {

    Dora_ComposeTheme{
        CalendarTopBar(
            pagerState = rememberPagerState(initialPage = 1, pageCount = {3})
        )
    }
}