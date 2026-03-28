package com.univ.doraboda_compose.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import java.time.LocalDate

@Composable
fun CalendarDialog(onChangeDate: (LocalDate) -> Unit){
    Dialog(
        onDismissRequest = {}
    ) {
        Column() {

        }
    }
}