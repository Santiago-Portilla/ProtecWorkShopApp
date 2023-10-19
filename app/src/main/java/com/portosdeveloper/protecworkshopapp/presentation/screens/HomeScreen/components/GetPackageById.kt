package com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecworkshopapp.domain.module.Response
import com.portosdeveloper.protecworkshopapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.HomeScreenViewModel
import com.portosdeveloper.protecworkshopapp.domain.module.Package
import com.portosdeveloper.protecworkshopapp.presentation.components.QRCodeGenerator
import com.portosdeveloper.protecworkshopapp.presentation.ui.theme.Red900


@Composable
fun GetPackageById(
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    when( val packageShirtResponse = viewModel.getPackageResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
            Text(text = "Ubicacion = ${packageShirtResponse.data.ubication}")
            if(viewModel.userInBBDD.job == "Terminacion"){
                if(packageShirtResponse.data.mergedFistsNecks.isNotEmpty()){
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "Cuellos listos")
                }else{
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "Cuellos No listos",
                        color = Red900
                    )

                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .border(1.dp, Color.Black),
                backgroundColor = Color.White
            ) {
                Column() {
                    Row() {
                        QRCodeGenerator(
                            modifier = Modifier.padding(3.dp),
                            text = packageShirtResponse.data.id,
                            size = 200
                        )
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, top = 15.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = "Id",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 10.dp),
                                    text = packageShirtResponse.data.id,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, top = 15.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = "Nombre",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 10.dp),
                                    text = packageShirtResponse.data.name,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, top = 15.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = "Cantidad",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 10.dp, bottom = 10.dp),
                                    text = packageShirtResponse.data.quantity,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }
                    }
                }
            }
            LaunchedEffect(Unit){
                viewModel.packageShirtInBBDD = packageShirtResponse.data
                when(viewModel.userInBBDD.job ){
                    "CuellosPuños" ->{
                        if(packageShirtResponse.data.mergedFistsNecks == ""){
                            if (packageShirtResponse.data.cutNecksFistsJob == "") {
                                viewModel.getLastUserById(packageShirtResponse.data.mergedJob)
                            }else{
                                if(packageShirtResponse.data.necksFistsJob == ""){
                                viewModel.getLastUserById(packageShirtResponse.data.cutNecksFistsJob)
                                }else {
                                    viewModel.getLastUserById(packageShirtResponse.data.necksFistsJob)
                                }
                            }
                        }
                    }
                    else ->{
                        when(packageShirtResponse.data.ubication){
                            "Corte"->{
                                viewModel.getLastUserById(packageShirtResponse.data.cutJob)
                            }
                            "Fusionado"->{
                                when(viewModel.userInBBDD.job){
                                    "Fusionado"->{
                                        viewModel.getLastUserById(packageShirtResponse.data.necksFistsJob)
                                    }
                                    else ->{
                                        viewModel.getLastUserById(packageShirtResponse.data.mergedJob)
                                    }
                                }
                            }
                            "Cuerpos"->{
                                when(viewModel.userInBBDD.job){
                                    "Fusionado" ->{
                                        viewModel.getLastUserById(packageShirtResponse.data.necksFistsJob)
                                    }
                                    "Cerrado" ->{
                                        viewModel.getLastUserById(packageShirtResponse.data.bodiesJob)
                                    }
                                }
                            }
                            "Cerradora"->{
                                when(viewModel.userInBBDD.job){
                                    "Fusionado"->{
                                        if(viewModel.mergedNeckFist){
                                            viewModel.getLastUserById(packageShirtResponse.data.necksFistsJob)
                                        }else if(viewModel.mergedClosed){
                                            viewModel.getLastUserById(packageShirtResponse.data.closedJob)
                                        }
                                    }
                                }
                            }
                            "CerradoraOk"->{
                                viewModel.getLastUserById(packageShirtResponse.data.mergedClosed)
                            }
                            "Terminacion"->{
                                viewModel.getLastUserById(packageShirtResponse.data.terminationJob)
                            }
                            "Ojal y Boton"->{
                                viewModel.getLastUserById(packageShirtResponse.data.buttonHoleButtonJob)
                            }
                            "Remate"->{
                                viewModel.getLastUserById(packageShirtResponse.data.polishJob)
                            }
                            "Emapaque"->{
                                viewModel.getLastUserById(packageShirtResponse.data.packingJob)

                            }
                        }
                    }
                }
            }
        }


        is Response.Failure ->{
            Toast.makeText(LocalContext.current, packageShirtResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{}
    }


}

