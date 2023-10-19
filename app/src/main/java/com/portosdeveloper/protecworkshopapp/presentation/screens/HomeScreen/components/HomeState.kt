package com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.components

data class HomeState(
    var id : String = "",
    var shirtPackage: String = "",
    var job : String = "",
    var idPackage: String = "",
    var quantityPackage: String ="",
    var restartActivity: Boolean = true,
    var userList: List<String> = listOf(),
    var packageList: List<String> = listOf(),
    var mergedUserList: List<String> = listOf(),
    var listClosed: List<String> = listOf("Con Pespunte","Sin Pespunte"),
    var listClosedResponse: String = ""
)