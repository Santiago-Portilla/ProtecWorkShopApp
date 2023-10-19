package com.portosdeveloper.protecworkshopapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.HomeScreen
import com.portosdeveloper.protecworkshopapp.presentation.screens.LoginScreen.LoginScreen

@Composable
fun AppNavigation (navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route)
    {
        composable(route = AppScreen.Login.route){
            LoginScreen(navController)
        }
        composable( route = AppScreen.Home.route){
            HomeScreen()
        }
    }
}