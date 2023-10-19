package com.portosdeveloper.protecworkshopapp.presentation.navigation

sealed class AppScreen(val route : String){
    object Login: AppScreen("login")
    object Home: AppScreen("home")
}
