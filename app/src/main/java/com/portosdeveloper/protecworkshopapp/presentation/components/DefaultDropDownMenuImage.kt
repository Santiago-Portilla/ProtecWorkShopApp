package com.portosdeveloper.protecworkshopapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portosdeveloper.protecworkshopapp.presentation.ui.theme.Red900

@Composable
fun DefaultDropDownMenuImage(
    modifierOutlineTextField: Modifier,
    modifierDropDownMenu: Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    validateField:() -> Unit = {},
    label: String,
    image: Painter,
    errorMsg: String = "",
    readOnly: Boolean = false,
    listItem: List<String>,
    onClick: (value: String) -> Unit = {}
){

    var expanded by remember { mutableStateOf(false) }

    val iconEnd = if(expanded){
        Icons.Default.KeyboardArrowUp
    }else{
        Icons.Default.KeyboardArrowDown
    }


    Column() {
        OutlinedTextField(
            modifier = modifierOutlineTextField,
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            label = {
                Text(
                    text = label,
                    color = Color.Black
                )
            },
            leadingIcon = {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = image,
                    contentDescription = "",
                )
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable { expanded = !expanded },
                    imageVector = iconEnd,
                    contentDescription = "",
                    tint = Color.Black
                )
            },
            readOnly = readOnly

        )
        DropdownMenu(
            modifier = modifierDropDownMenu,
            expanded = expanded,
            onDismissRequest = {expanded = false}) {
            listItem.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onClick(item)
                        validateField()
                        expanded = false
                    }
                ) {
                    Text(item)
                }
            }
        }
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = errorMsg,
            fontSize = 11.sp,
            color = Red900
        )

    }
}