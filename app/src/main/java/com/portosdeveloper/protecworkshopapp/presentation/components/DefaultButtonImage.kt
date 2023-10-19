package com.portosdeveloper.protecworkshopapp.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.portosdeveloper.protecworkshopapp.presentation.ui.theme.Blue700

@Composable
fun DefaultButtonImage(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    color: Color = Blue700,
    icon: Painter,
    enabled: Boolean = true
){
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        enabled = enabled
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = icon,
            contentDescription ="" )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text)
    }
}