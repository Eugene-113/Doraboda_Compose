package com.univ.doraboda_compose.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PickerTextField(
    initText: String,
    onTextChanged: (String) -> Unit
){
    var thisText by remember { mutableStateOf(initText) }
    LaunchedEffect(thisText) {
        onTextChanged(thisText)
    }

    BasicTextField(
        value = thisText,
        onValueChange = { newValue ->
            if(newValue.all { it.isDigit() }){
                thisText = newValue
            }
                        },
        modifier = Modifier
            .width(80.dp)
            .height(20.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    ){ innerTextField ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(3.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            innerTextField()
        }
    }
}

@Preview
@Composable
fun PreviewPickerTextField() {
    MaterialTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            PickerTextField("init") { }
        }
    }
}