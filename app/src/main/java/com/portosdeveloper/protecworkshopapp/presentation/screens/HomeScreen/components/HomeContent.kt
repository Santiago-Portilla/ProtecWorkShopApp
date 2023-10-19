package com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.components


import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.portosdeveloper.protecworkshopapp.R
import com.portosdeveloper.protecworkshopapp.domain.module.User
import com.portosdeveloper.protecworkshopapp.presentation.components.DefaultButton
import com.portosdeveloper.protecworkshopapp.presentation.components.DefaultInputTextField
import com.portosdeveloper.protecworkshopapp.presentation.screens.HomeScreen.HomeScreenViewModel
import com.portosdeveloper.protecworkshopapp.domain.module.Package
import com.portosdeveloper.protecworkshopapp.presentation.components.DefaultButtonImage
import com.portosdeveloper.protecworkshopapp.presentation.ui.theme.Red700

@Composable
fun HomeContent(viewModel: HomeScreenViewModel = hiltViewModel()){


    val context = LocalContext.current
    val state = viewModel.state

    if(viewModel.restartActivity){
        viewModel.idInput("")
        viewModel.shirtPackageInput("")
        viewModel.userInBBDD = User()
        viewModel.packageShirtInBBDD = Package()
        viewModel.lastUserInBBDD = User()
        viewModel.appearButtonSearch = false
        viewModel.appearButtonConfirm = false
        viewModel.appearPackageInfo = false
        viewModel.mergedNeckFist = false
        viewModel.mergedClosed = false
        viewModel.selectedNecksOrClosed = true
    }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data : Intent? = result.data
        val scanResult: IntentResult? = IntentIntegrator.parseActivityResult(result.resultCode,data)
        if(scanResult != null && scanResult.contents != null){
            when(state.id){
                ""->{
                    viewModel.idInput(scanResult.contents)
                }
                else ->{
                    viewModel.shirtPackageInput(scanResult.contents)
                }
            }
        }else{
            Toast.makeText(context, "Escaneo cancelado o inválido", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            DefaultInputTextField(
                modifier = Modifier,
                state = state.id,
                onValueChange = {viewModel.idInput(it)},
                validateField = {},
                validateTotal = {},
                label ="Ingresar Usuario",
                readOnly = true
            )
            DefaultButtonImage(
                modifier = Modifier
                    .padding(10.dp)
                    .height(51.dp),
                text = "",
                onClick = {
                    viewModel.idInput("")
                    val integrator = IntentIntegrator(context as ComponentActivity)
                    integrator.setOrientationLocked(false)
                    integrator.setPrompt("Scan a QR Code")
                    launcher.launch(integrator.createScanIntent())
                    viewModel.restartActivity = false
                },
                icon = painterResource(id = R.drawable.icon_search)
            )
            BackHandler {
                // Handle back button if needed
            }
        }
        if(state.id == ""){
            Text(
                text = viewModel.errorMessageUser,
                color = Red700,
                fontSize = 15.sp
            )
            viewModel.isValidUser = false
        }else if(!state.userList.contains(state.id)){
            Text(
                text = viewModel.errorMessageUserNotMatch,
                color = Red700,
                fontSize = 15.sp
            )
            viewModel.isValidUser = false
        }else{
            viewModel.isValidUser = true
        }
        if(state.mergedUserList.contains(state.id) && viewModel.selectedNecksOrClosed){
            Column(){
                DefaultButton(
                    modifier = Modifier,
                    text = "Corte",
                    onClick = {
                        viewModel.selectedNecksOrClosed = false
                    }
                )
                DefaultButton(
                    modifier = Modifier,
                    text = "CuellosPunios",
                    onClick = {
                        viewModel.mergedNeckFist = true
                        viewModel.selectedNecksOrClosed = false
                    }
                )
                DefaultButton(
                    modifier = Modifier,
                    text = "Cerradora",
                    onClick = {
                        viewModel.mergedClosed = true
                        viewModel.selectedNecksOrClosed = false
                    }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            DefaultInputTextField(
                modifier = Modifier,
                state = state.shirtPackage,
                onValueChange = {viewModel.shirtPackageInput(it)},
                validateTotal = {},
                label ="Ingresar Paquete",
                readOnly = true
            )
            DefaultButtonImage(
                modifier = Modifier
                    .padding(10.dp)
                    .height(51.dp),
                text = "",
                onClick = {
                    viewModel.shirtPackageInput("")
                    val integrator = IntentIntegrator(context as ComponentActivity)
                    integrator.setOrientationLocked(false)
                    integrator.setPrompt("Scan a QR Code")
                    launcher.launch(integrator.createScanIntent())
                    viewModel.appearButtonSearch = true
                },
                icon = painterResource(id = R.drawable.icon_search)
            )
        }
        if(state.shirtPackage == ""){
            Text(
                text = viewModel.errorMessagePackage,
                color = Red700,
                fontSize = 15.sp
            )
            viewModel.isValidPackage = false
        }else if(!state.packageList.contains(state.shirtPackage)){
            Text(
                text = viewModel.errorMessagePackageNotMatch,
                color = Red700,
                fontSize = 15.sp
            )
            viewModel.isValidPackage = false
        }else{
            viewModel.isValidPackage = true
        }
        if (viewModel.appearButtonSearch){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                DefaultButtonImage(
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp),
                    text = "Buscar",
                    onClick = {
                        viewModel.getUserById(state.id)
                        viewModel.appearPackageInfo = true


                    },
                    icon = painterResource(id = R.drawable.icon_search),
                    enabled = viewModel.isValidUser && viewModel.isValidPackage
                )
                DefaultButtonImage(
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp),
                    text = "Borrar",
                    onClick = {
                        viewModel.restartActivity = true
                    },
                    icon = painterResource(id = R.drawable.icon_erraser)
                )
            }
            if(viewModel.appearPackageInfo){
                GetPackageById()
                GetUserById()
                GetLastUserById()
                if(viewModel.packageShirtInBBDD.ubication == "Cerradora"){
                    if(viewModel.mergedClosed){
                        state.listClosed.forEachIndexed { index, option ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .selectable(
                                        selected = (option == state.listClosedResponse),
                                        onClick = {
                                            viewModel.listClosedResponseInput(option)
                                            viewModel.isValidClose = true
                                        }
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = ( option == state.listClosedResponse ),
                                    onClick = {
                                        viewModel.listClosedResponseInput(option)
                                        viewModel.isValidClose = true
                                    }
                                )
                                Row() {
                                    Text(
                                        modifier = Modifier
                                            .padding( start = 12.dp),
                                        text = option
                                    )
                                }

                            }
                        }
                    }
                }else{
                    viewModel.isValidClose = true
                }
            }
        }
        if (viewModel.appearButtonConfirm){
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                text = "Confirmar",
                onClick = {
                    viewModel.onUpdatePackage()
                    viewModel.onUpdateUser()
                    viewModel.onUpdateLastUser()
                    viewModel.onUpdateProcess()
                    if(viewModel.confirmUpdatePU) Toast.makeText(context, "Se a subido de manera correcta", Toast.LENGTH_LONG).show()
                },
                enabled = viewModel.isValidClose
            )
        }

    }
}
