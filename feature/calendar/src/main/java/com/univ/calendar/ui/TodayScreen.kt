package com.univ.calendar.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.univ.ui.DoraEmotions
import com.univ.ui.R
import com.univ.ui.theme.Dora_ComposeTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TodayScreen(
    thisDate: LocalDate,
    thisEmotion: String?,
    updateEmotion: () -> Unit = {},
    deleteEmotion: () -> Unit = {}
) {
    val thisEmotionIndex = DoraEmotions.emotionNames.indexOf(thisEmotion)
    val emotionImage = DoraEmotions.emotionImgs[thisEmotionIndex]
    var showModalSheet by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(1) }
    val format = DateTimeFormatter.ofPattern("yyyy년 M월 dd일")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colorScheme.background)
    ){
        Spacer(modifier = Modifier
            .height(20.dp)
        )
        Text(
            text = thisDate.format(format),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier
            .height(30.dp)
        )
        Image(
            painter = painterResource(emotionImage),
            modifier = Modifier.size(150.dp)
                .clickable(onClick = { showModalSheet = true })
            ,
            contentDescription = null
        )
        Spacer(modifier = Modifier
            .height(30.dp)
        )
        Card(shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ){
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "오늘의 한마디",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(17.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.icon_edit),
                        modifier = Modifier.size(50.dp),
                        contentDescription = null
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "오늘의 한마디",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(17.dp)
                    )
                }
            }
        }
    }
    if(showModalSheet){
        EmotionSelectScreen(defaultIndex = 0) { i ->
            showModalSheet = false
            if(thisEmotion != DoraEmotions.emotionNames[i%7]){
                //if value changed, viewmodel insert
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodayScreenPreview() {
    Dora_ComposeTheme{
        TodayScreen(thisDate = LocalDate.now(), thisEmotion = "empty", updateEmotion = {}) { }
    }
}