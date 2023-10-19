package com.portosdeveloper.protecworkshopapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portosdeveloper.protecworkshopapp.presentation.ui.theme.Gray700

@Composable
fun DefaultCard(
    modifier: Modifier,
    image: Painter,
    title: String
){
    Card(
        modifier = modifier,
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Gray700
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp),
                    painter = image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                text = title,
                color = Color.White,
                fontSize = 20.sp
            )
        }

    }




}

