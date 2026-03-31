package com.univ.doraboda_compose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.univ.doraboda_compose.theme.Dora_ComposeTheme
import com.univ.doraboda_compose.theme.White100
import java.time.LocalDate

@Composable
fun CalendarDialog(
    onClickDismiss: () -> Unit,
    onClickDone: (LocalDate) -> Unit,
    defaultLocalDate: LocalDate,
    minYear: Int = 2000,
    maxYear: Int = 2999
    ){
    Dialog(
        onDismissRequest = onClickDismiss
    ) {
        val focusManager = LocalFocusManager.current
        var yearState by remember { mutableStateOf(defaultLocalDate.year) }
        var monthState by remember { mutableStateOf(defaultLocalDate.monthValue) }
        var yearClicked by remember { mutableStateOf(false) }
        var monthClicked by remember { mutableStateOf(false) }
        val yearList = (minYear..maxYear).toList()

        Card(shape = RoundedCornerShape(8.dp)) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = White100)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            focusManager.clearFocus()
                        }
                    )
                ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier
                    .height(30.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    if(!yearClicked){
                        ListPicker(
                            itemList = yearList,
                            indexChanged = { index -> yearState = index },
                            initIndex = yearList.size * 2 + yearList.indexOf(yearState) - 1
                        ) {
                            yearClicked = true
                        }
                    } else {
                        PickerTextField(initText = yearState.toString()) { numberString ->
                            val stringToNum = numberString.toInt()
                            yearClicked = false
                            if(stringToNum in minYear..maxYear){
                                yearState = stringToNum
                            }
                        }
                    }

                    Text(
                        text = "년",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(10.dp)
                    )

                    if(!monthClicked){
                        ListPicker(
                            itemList = (1..12).toList(),
                            indexChanged = { index -> monthState = index },
                            initIndex = 12 * 2 + (monthState - 1) - 1
                        ) {
                            monthClicked = true
                        }
                    } else {
                        PickerTextField(initText = monthState.toString()) { numberString ->
                            val stringToNum = numberString.toInt()
                            monthClicked = false
                            if(stringToNum in 1..12){
                                monthState = stringToNum
                            }
                        }
                    }

                    Text(
                        text = "월",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier
                    .height(30.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(
                        onClick = onClickDismiss,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "취소",
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Spacer(modifier = Modifier
                        .width(30.dp)
                    )
                    Button(
                        onClick = {
                            onClickDone(LocalDate.of(yearState, monthState, 1))
                        },
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "확인",
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCalendarDialog() {
    Dora_ComposeTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            CalendarDialog(onClickDismiss = {},
                onClickDone = { date -> },
                defaultLocalDate = LocalDate.of(2000, 1, 1))
        }
    }
}