package com.cherry.guessthething.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cherry.guessthething.presentation.screens.QuizResultScreen
import com.cherry.guessthething.presentation.screens.QuizScreen
import com.cherry.guessthething.presentation.screens.StartScreen

@Composable
fun MainNavHost(navHostController: NavHostController, viewModel: CartoonViewModel) {
    NavHost(navController = navHostController, startDestination = Screen.StartScreen.route) {
        composable(
            route = Screen.StartScreen.route
        ) {
            StartScreen(navController = navHostController, viewModel = viewModel)
        }
        composable(
            route = Screen.QuizScreen.route
        ) {
            QuizScreen(navController = navHostController, viewModel = viewModel)
        }

        composable(
            route = Screen.QuizResultScreen.route,
            arguments = listOf(navArgument(CURRENT_RESULT_KEY) {
                type = NavType.IntType
            })
        ) {
            QuizResultScreen(
                navController = navHostController,
                viewModel = viewModel,
                currentResult = it.arguments?.getInt(
                    CURRENT_RESULT_KEY, -1
                ) ?: -1
            )
        }
    }
}