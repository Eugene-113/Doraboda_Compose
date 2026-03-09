package com.univ.doraboda_compose.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.univ.doraboda_compose.navigation.DoraScreen
import com.univ.doraboda_compose.ui.main.MainScreen

@Composable
fun DoraApp(){
    val mainNavController = rememberNavController()
    NavHost(
        navController = mainNavController,
        startDestination = DoraScreen.Main
    ){
        composable<DoraScreen.Main> {
            MainScreen(
                content = {}
            )
        }
        composable<DoraScreen.DailyDetail> {

        }
        composable<DoraScreen.DailyWrite> {

        }
        composable<DoraScreen.Settings> {

        }
    }
}