package com.cherry.guessthething.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cherry.guessthething.presentation.CartoonViewModel
import com.cherry.guessthething.presentation.Screen
import com.cherry.guessthething.presentation.components.ProjectOutlinedButton
import kotlinx.coroutines.delay

@Composable
fun StartScreen(navController: NavHostController, viewModel: CartoonViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        var listIsLoaded by remember {
            mutableStateOf(viewModel.isLoaded)
        }
        LaunchedEffect(key1 = listIsLoaded) {
            while (!listIsLoaded) {
                listIsLoaded = viewModel.isLoaded
                delay(1000)
            }
        }
        if (!listIsLoaded) {
            CircularProgressIndicator()
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "if your answer is right background will be (or stays) green\notherwise you're wrong\nalso, you have 30 secs",
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                ProjectOutlinedButton(text = "Play Quiz", onClick = {
                    navController.navigate(Screen.QuizScreen.route) {
                        popUpTo(Screen.StartScreen.route) {
                            inclusive = true
                        }
                    }

                })
            }
        }
    }

}