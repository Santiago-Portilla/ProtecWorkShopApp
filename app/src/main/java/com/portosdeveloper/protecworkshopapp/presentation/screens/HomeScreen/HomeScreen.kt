package com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.components.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(){

    Scaffold(
        content = {
            HomeContent()
        },
    )
    UpdateUser()
    UpdatePackage()
    UpdateSalary()
    UpdateFinish()
    UpdateProcess()
}


