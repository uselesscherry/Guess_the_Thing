package com.cherry.guessthething.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.cherry.guessthething.domain.CartoonViewModel

@Composable
fun QuizScreen(viewModel: CartoonViewModel) {
    Card(
        modifier = Modifier
            .fillMaxSize(0.9f),
        elevation = 0.dp,
        border = BorderStroke(1.dp, Color.Blue.copy(alpha = 0.7f))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {

            }
        }
    }
}