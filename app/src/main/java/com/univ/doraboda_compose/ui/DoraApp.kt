package com.univ.doraboda_compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.univ.doraboda_compose.navigation.DoraScreen
import com.univ.doraboda_compose.theme.Dora_ComposeTheme
import com.univ.doraboda_compose.ui.calendar.CalendarScreen

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
                .background(color = MaterialTheme.colorScheme.background)
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
    BottomAppBar(containerColor = MaterialTheme.colorScheme.surface) {

    }
}

@Preview(showBackground = true)
@Composable
fun DoraPreview() {
    Dora_ComposeTheme{
        DoraApp()
    }
}