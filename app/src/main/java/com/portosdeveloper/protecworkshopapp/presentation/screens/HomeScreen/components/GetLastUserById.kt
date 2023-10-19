package com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.components

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecworkshopapp.domain.module.Response
import com.portosdeveloper.protecworkshopapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.HomeScreenViewModel
import com.portosdeveloper.protecworkshopapp.presentation.utils.ActualDate

@Composable
fun GetLastUserById(viewModel: HomeScreenViewModel = hiltViewModel()){

    when(val lastUserResponse = viewModel.getLastUserResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
            LaunchedEffect(Unit){
                viewModel.lastUserInBBDD = lastUserResponse.data
            }
            Row(){
                Text(text = "Persona que Entrega : ")
                Text(
                    text = "${lastUserResponse.data.name} ${lastUserResponse.data.surName}",
                    fontWeight = FontWeight.Bold
                )
                viewModel.appearButtonConfirm = true
            }
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, lastUserResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{}
    }


}