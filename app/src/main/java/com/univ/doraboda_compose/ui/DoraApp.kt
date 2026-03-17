package com.univ.doraboda_compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.univ.doraboda_compose.navigation.DoraScreen
import com.univ.doraboda_compose.ui.main.MainGraph
import com.univ.doraboda_compose.ui.main.MainScreen

@Composable
fun DoraApp(){
    val doraNavController = rememberNavController()
    NavHost(
        navController = doraNavController,
        startDestination = DoraScreen.Main
    ){
        composable<DoraScreen.Main> {
            val mainNavController = rememberNavController()
            MainScreen(){
                MainGraph(navController = mainNavController)
            }
        }
        composable<DoraScreen.DailyDetail> {

        }
        composable<DoraScreen.DailyWrite> {

        }
        composable<DoraScreen.Settings> {

        }
    }
}