package com.cherry.guessthething.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.cherry.guessthething.presentation.CartoonViewModel
import com.cherry.guessthething.presentation.Screen

@Composable
fun QuizResultScreen(
    navController: NavHostController,
    viewModel: CartoonViewModel,
    currentResult: Int
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "your result:$currentResult")
        Text(text = "max result:${viewModel.maxResult.collectAsState(initial = -1).value}")
        Button(onClick = {
            navController.navigate(Screen.QuizScreen.route) {
                popUpTo(Screen.QuizScreen.route) {
                    inclusive = true
                }
            }
        }) {
            Text(text = "play again")
        }
    }
}