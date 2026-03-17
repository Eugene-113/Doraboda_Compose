package com.univ.doraboda_compose.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.univ.doraboda_compose.navigation.DoraScreen

@Composable
fun MainGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = DoraScreen.DoraCalendar
    ){
        composable<DoraScreen.DoraCalendar>{}
        composable<DoraScreen.DoraMusicPlayer>{}
        composable<DoraScreen.DoraData>{}
    }
}