package com.univ.calendar.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.univ.ui.DoraEmotions
import com.univ.ui.R
import com.univ.ui.theme.Dora_ComposeTheme
import com.univ.ui.theme.GreyYellow90
import com.univ.ui.theme.TodayYellow
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmotionSelectScreen(defaultIndex: Int, onDismiss: (Int) -> Unit) {
    var selectedIndex by remember { mutableStateOf(2) }

    ModalBottomSheet(onDismissRequest = { onDismiss(selectedIndex) },
        containerColor = TodayYellow
        ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .background(TodayYellow)
        ) {
            items(7){ index ->
                val realIndex = index + 1
                EmotionView(realIndex, selectedIndex) { i ->
                    selectedIndex = i
                }
            }
        }
        Spacer(modifier = Modifier
            .height(20.dp)
        )
    }
}

@Composable
fun EmotionView(realIndex: Int, selectedIndex: Int, onClick: (Int) -> Unit){
    val color =
    if(selectedIndex == realIndex) GreyYellow90
    else Color.Transparent

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .clickable(onClick = { onClick(realIndex) })
            .size(120.dp)
            .padding(7.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
            .background(color),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(DoraEmotions.emotionImgs[realIndex]),
                modifier = Modifier.size(100.dp),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmotionSelectPreview() {
    Dora_ComposeTheme{
        EmotionSelectScreen(defaultIndex = 1) {  }
    }
}