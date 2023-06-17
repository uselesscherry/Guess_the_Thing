package com.moya_utoma.result_ui.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.FilledTonalButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun QuizResultScreen(
    navigateToWelcomeScreen: () -> Unit,
    viewModel: QuizResultViewModel = hiltViewModel(),
    currentResult: Int
) {

    val state = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "your result: $currentResult")
        Text(text = "max result: ${state.value.maxResult}")
        FilledTonalButton(onClick = navigateToWelcomeScreen) {
            Text(text = "Play again")
        }
    }
}