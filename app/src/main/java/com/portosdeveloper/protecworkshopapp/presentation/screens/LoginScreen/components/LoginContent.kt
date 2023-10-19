package com.portosdeveloper.protecworkshopapp.presentation.screens.LoginScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portosdeveloper.protecworkshopapp.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecworkshopapp.presentation.screens.LoginScreen.LoginViewModel
import com.portosdeveloper.protecworkshopapp.presentation.components.DefaultTextField
import com.portosdeveloper.protecworkshopapp.presentation.components.DefaultButton
import com.portosdeveloper.protecworkshopapp.presentation.ui.theme.Gray500

@Composable
fun LoginContent(viewModel: LoginViewModel = hiltViewModel()){

    val state = viewModel.state

    Box() {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(bottom = 600.dp)
                    .size(650.dp),
                painter = painterResource(id = R.drawable.icon_portec),
                contentDescription = "Company Logo"
            )

        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 150.dp),
                text = "TALLER APP",
                fontWeight = FontWeight.Bold,
            )
        }

        Card(
            modifier = Modifier
                .padding(
                    start = 40.dp,
                    end = 40.dp,
                    top = 200.dp),
            backgroundColor = Gray500
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 40.dp,
                            bottom = 0.dp,
                            start = 0.dp,
                            end = 0.dp),
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold

                )
                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "Porfavor Inicia Sesion para Continuar",
                    fontSize = 12.sp,
                    color = Color.Black
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = state.email ,
                    onValueChange = { email -> viewModel.onEmailInput(email) },
                    validateField = { viewModel.validateEmail() },
                    label = "Correo Electronico",
                    icon = Icons.Default.Email,
                    keyboartype = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg,

                    )

                DefaultTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = state.password,
                    onValueChange = { password -> viewModel.onPasswordInput(password) },
                    validateField = { viewModel.validatePassword() },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg,

                    )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 45.dp,
                            bottom = 45.dp
                        ),
                    text = "INICIAR SESION",
                    onClick = {
                        viewModel.login()
                    },
                    enabled = viewModel.isEnabledLoginButton
                )
            }
        }
    }
}