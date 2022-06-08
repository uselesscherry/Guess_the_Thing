package com.cherry.guessthething.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.cherry.guessthething.CartoonViewModel

@Composable
fun QuizResultScreen(navController: NavHostController,viewModel: CartoonViewModel,currentResult:Int) {
Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly
) {
    Text(text = "your result:$currentResult")
    Text(text = "max result:${viewModel.maxResult}")
}
}