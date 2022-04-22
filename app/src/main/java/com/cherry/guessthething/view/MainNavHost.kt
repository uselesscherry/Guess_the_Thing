package com.cherry.guessthething.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cherry.guessthething.CartoonViewModel

@Composable
fun MainNavHost(navHostController: NavHostController,viewModel: CartoonViewModel) {
NavHost(navController = navHostController, startDestination = Screen.StartScreen.route ){
    composable(
        route = Screen.StartScreen.route
    ){
        StartScreen(navHostController = navHostController, viewModel = viewModel)
    }
    composable(
        route =  Screen.QuizScreen.route
    ){
        QuizScreen(viewModel = viewModel)
    }
}
}