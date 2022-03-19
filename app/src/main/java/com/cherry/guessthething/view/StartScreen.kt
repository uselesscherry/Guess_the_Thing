package com.cherry.guessthething.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cherry.guessthething.domain.CartoonViewModel
import kotlinx.coroutines.delay

@Composable
fun StartScreen(viewModel: CartoonViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        var listIsLoaded by remember {
            mutableStateOf(viewModel.isLoaded)
        }
        LaunchedEffect(key1 = listIsLoaded){
            while (!listIsLoaded){
            listIsLoaded = viewModel.isLoaded

                delay(1000)
            }

        }
        if (!listIsLoaded) {
            CircularProgressIndicator()
        }
        else{
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                for (i in viewModel.cartoons){
                    Text(text = i.name)
                }
            }
        }
    }

}