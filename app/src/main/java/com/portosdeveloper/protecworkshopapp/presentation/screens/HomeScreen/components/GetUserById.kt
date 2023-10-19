package com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecworkshopapp.domain.module.Response
import com.portosdeveloper.protecworkshopapp.domain.module.User
import com.portosdeveloper.protecworkshopapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.HomeScreenViewModel
import com.portosdeveloper.protecworkshopapp.presentation.utils.ActualDate

@Composable
fun GetUserById(
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    when( val userResponse = viewModel.getUserResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
            LaunchedEffect(Unit){
                viewModel.userInBBDD = userResponse.data
                viewModel.jobInput(userResponse.data.job)
                viewModel.getPackageById(viewModel.state.shirtPackage)
            }
            Row(){
                Text(text = "Persona que Recibe : ")
                Text(
                    text = "${userResponse.data.name} ${userResponse.data.surName}",
                    fontWeight = FontWeight.Bold
                )
            }

        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, userResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()

        }
        else ->{}
    }
}