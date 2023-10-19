package com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecworkshopapp.domain.module.Response
import com.portosdeveloper.protecworkshopapp.domain.use_cases.user.UpdateSalary
import com.portosdeveloper.protecworkshopapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.HomeScreenViewModel

@Composable
fun UpdateSalary(
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    when( val updateSalaryUserResponse = viewModel.updateSalaryUserResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, updateSalaryUserResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{}
    }

}