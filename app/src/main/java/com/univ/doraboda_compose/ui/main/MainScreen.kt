package com.univ.doraboda_compose.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.univ.doraboda_compose.theme.Dora_ComposeTheme

@Composable
fun MainScreen(
    content: @Composable () -> Unit
){
    Scaffold(
        bottomBar = { DoraBottomBar() }
    ){ paddingValues ->
        Box(
            Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = MaterialTheme.colorScheme.background)
        ){
            content()
        }
    }
}

@Composable
fun DoraBottomBar(){
    BottomAppBar(containerColor = MaterialTheme.colorScheme.surface) {

    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Dora_ComposeTheme{
        MainScreen {  }
    }
}