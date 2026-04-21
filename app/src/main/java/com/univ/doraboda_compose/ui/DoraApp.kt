package com.univ.doraboda_compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.univ.calendar.ui.CalendarScreen
import com.univ.doraboda_compose.navigation.DoraScreen
import com.univ.ui.theme.Dora_ComposeTheme
import com.univ.ui.theme.GreyYellow90
import com.univ.ui.theme.TodayYellow

@Composable
fun DoraApp(){
    val doraNavController = rememberNavController()
    Scaffold(
        bottomBar = { DoraBottomBar() }
    ){ paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = TodayYellow)
        ){
            NavHost(
                navController = doraNavController,
                startDestination = DoraScreen.DoraCalendar
            ){
                composable<DoraScreen.DoraCalendar>{
                    CalendarScreen()
                }
                composable<DoraScreen.DoraMusicPlayer>{}
                composable<DoraScreen.DoraData>{}

                composable<DoraScreen.DailyDetail> {

                }
                composable<DoraScreen.DailyWrite> {

                }
                composable<DoraScreen.Settings> {

                }
            }
        }
    }
}

@Composable
fun DoraBottomBar(){
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .dropShadow(
                shape = RoundedCornerShape(30.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    spread = 6.dp,
                    color = GreyYellow90
                )
            )
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun DoraPreview() {
    Dora_ComposeTheme{
        DoraApp()
    }
}