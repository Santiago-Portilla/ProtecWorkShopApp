package com.portosdeveloper.protecworkshopapp.presentation.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.portosdeveloper.protecworkshopapp.presentation.ui.theme.Blue700

@Composable
fun DefaultTopBar (
    title: String,
    color: Color = Color.Black,
    upAvailable: Boolean = false,
    navController: NavHostController? = null
){

    TopAppBar (
        title = {
            Text(
                text = title,
                fontSize = 19.sp,
                color = color,
                textAlign = TextAlign.Center,

            )
        },
        backgroundColor = Blue700,
        navigationIcon = {
            if(upAvailable){
                IconButton(onClick = { navController?.popBackStack()}) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    )

}