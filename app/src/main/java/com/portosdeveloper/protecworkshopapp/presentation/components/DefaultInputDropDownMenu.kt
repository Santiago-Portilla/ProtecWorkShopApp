package com.portosdeveloper.protecworkshopapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultInputDropDownMenu(
    state: String,
    modifier: Modifier,
    modifierDropDownMenu: Modifier,
    onValueChange: (value: String) -> Unit,
    validateField:() -> Unit = {},
    validateList: () -> Unit = {},
    validateTotal: () -> Unit = {},
    label: String,
    list: List<String>,
    errorMsg: String = ""
) {
        DefaultDropDownMenu(
            modifier = modifier,
            modifierDropDownMenu = modifierDropDownMenu,
            value = state,
            onValueChange = {
                onValueChange(it)
            },
            validateField = {
                validateField()
            },
            validateList = {
                validateList()
            },
            validateTotal ={
                validateTotal()
            },
            label = label,
            errorMsg = errorMsg,
            readOnly = true,
            listItem = list,
            onClick = {
                onValueChange(it)
            }
        )

}
