package com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecworkshopapp.domain.module.Response
import com.portosdeveloper.protecworkshopapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.HomeScreenViewModel

@Composable
fun UpdateFinish(
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    val context = LocalContext.current
    when( val updateLastUserResponse = viewModel.updateLastUserResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
            LaunchedEffect(Unit){
                Toast.makeText(context, "Se a subido de manera correcta", Toast.LENGTH_LONG).show()
                viewModel.restartActivity = true
            }
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, updateLastUserResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{}
    }

}