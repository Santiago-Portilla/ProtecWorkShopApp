package com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecworkshopapp.domain.module.Package
import com.portosdeveloper.protecworkshopapp.domain.module.Response
import com.portosdeveloper.protecworkshopapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.HomeScreenViewModel

@Composable
fun UpdatePackage(
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    when( val updatePackageResponse = viewModel.updatePackageResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{

        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, updatePackageResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{}
    }

}