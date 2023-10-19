package com.portosdeveloper.protecworkshopapp.presentation.screens.LoginScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.portosdeveloper.protecworkshopapp.presentation.screens.LoginScreen.components.LoginContent
import com.portosdeveloper.protecworkshopapp.presentation.screens.LoginScreen.components.Login
import com.portosdeveloper.protecworkshopapp.presentation.ui.theme.Blue700
import com.portosdeveloper.protecworkshopapp.presentation.ui.theme.Brown700
import com.portosdeveloper.protecworkshopapp.presentation.ui.theme.Gray700

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController){



    Scaffold(
        topBar = {
            Column() {
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 20.dp,
                    color = Blue700
                )
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 20.dp,
                    color = Brown700
                )
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 20.dp,
                    color = Gray700
                )
            }
        },
        content = { LoginContent() }
    )
    Login(navController = navController)
}