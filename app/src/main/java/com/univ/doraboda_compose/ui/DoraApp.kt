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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.univ.calendar.ui.CalendarScreen
import com.univ.calendar.ui.TodayScreen
import com.univ.calendar.viewmodel.TodayViewModel
import com.univ.doraboda_compose.navigation.DoraController
import com.univ.doraboda_compose.navigation.DoraScreen
import com.univ.ui.theme.Dora_ComposeTheme
import com.univ.ui.theme.GreyYellow90
import com.univ.ui.theme.TodayYellow
import java.time.LocalDate
import com.univ.calendar.viewmodel.TodayViewModel.TodayIntent

@Composable
fun DoraApp(){
    val mainNavController = rememberNavController()
    val doraController = DoraController(mainNavController)
    val backStack by mainNavController.currentBackStackEntryAsState()
    val routes = listOf(DoraScreen.DoraCalendar::class, DoraScreen.DoraData::class, DoraScreen.DoraMusicPlayer::class)
    val showBottomBar = routes.any{ route ->
        backStack?.destination?.hasRoute(route) == true
    }
    Scaffold(
        bottomBar = {
            if(showBottomBar) DoraBottomBar()
        }
    ){ paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = TodayYellow)
        ){
            NavHost(
                navController = mainNavController,
                startDestination = DoraScreen.DoraCalendar
            ){
                composable<DoraScreen.DoraCalendar>{
                    CalendarScreen(){ date, emotion ->
                        doraController.navigateToDailyDetail(date, emotion)
                    }
                }
                composable<DoraScreen.DoraMusicPlayer>{}
                composable<DoraScreen.DoraData>{}

                composable<DoraScreen.DailyDetail> { backStackEntry ->
                    val data = backStackEntry.toRoute<DoraScreen.DailyDetail>()
                    val viewModel = hiltViewModel<TodayViewModel>()
                    TodayScreen(thisDate =
                        LocalDate.of(data.year, data.month, data.day),
                        defaultEmotion = data.thisEmotion,
                        insertEmotion = { item ->
                            viewModel.handleIntent(TodayIntent.InsertEmotion(item)) },
                        deleteEmotion = {}
                    )
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