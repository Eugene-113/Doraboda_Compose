package com.univ.doraboda_compose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListPicker(
    itemList: List<Int>,
    itemsCount: Int = itemList.size * 4,
    visibleItemNumber: Int = 3,
    initIndex: Int = itemList.size * 2 - 1,
    indexChanged: (Int) -> Unit,
    onPickerClicked: () -> Unit
) {
    val focusedItemOffset = visibleItemNumber / 2
    val itemHeight = 20

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initIndex)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val fadingGradient = remember {
        Brush.verticalGradient(
            0f to Color.Transparent,
            0.5f to Color.Black,
            1f to Color.Transparent
        )
    }

    val thisIndex by remember {
        derivedStateOf { listState.firstVisibleItemIndex }
    }
    LaunchedEffect(thisIndex) {
        indexChanged(itemList[(thisIndex + focusedItemOffset) % itemList.size])
    }

    LazyColumn(
        state = listState,
        flingBehavior = flingBehavior,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .height((itemHeight * visibleItemNumber).dp)
            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
            .drawWithContent{
                drawContent()
                drawRect(brush = fadingGradient, blendMode = BlendMode.DstIn)
            }
            .clickable(onClick = onPickerClicked)
    ) {
        items(itemsCount){ index ->
            Text(
                text = itemList[index % itemList.size].toString(),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight.dp)
                ,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun PreviewNumberPicker() {
    MaterialTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            ListPicker(
                itemList = listOf(0, 1, 2, 3, 4),
                indexChanged = { index -> },
                initIndex = 0
            ){}
        }
    }
}