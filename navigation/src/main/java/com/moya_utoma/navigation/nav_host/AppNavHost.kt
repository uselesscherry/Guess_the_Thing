package com.moya_utoma.navigation.nav_host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moya_utoma.color_quiz_ui.ui.ColorQuizScreen
import com.moya_utoma.result_ui.ui.QuizResultScreen
import com.moya_utoma.welcome.ui.WelcomeScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreenRoute.WelcomeScreen.route) {
        composable(
            route = ScreenRoute.WelcomeScreen.route
        ) {
            WelcomeScreen(navigateToColorQuiz = navController::navigateToColorQuiz)
        }
        composable(
            route = ScreenRoute.ColorQuizScreen.route
        ) {
            ColorQuizScreen(navigateToResultScreen = navController::navigateToColorQuizResult)
        }

        composable(
            route = ScreenRoute.ColorQuizResultScreen.route,
            arguments = listOf(navArgument(CURRENT_RESULT_KEY) {
                type = NavType.IntType
            })
        ) {
            val currentResult = it.arguments?.getInt(
                CURRENT_RESULT_KEY, -1
            ) ?: -1
            QuizResultScreen(
                navigateToWelcomeScreen = navController::navigateToWelcomeScreen,
                currentResult = currentResult
            )
        }
    }
}

private fun NavHostController.navigateToColorQuiz() {
    navigate(ScreenRoute.ColorQuizScreen.route)
}

private fun NavHostController.navigateToColorQuizResult(currentResult: Int = -1) {
    navigate(ScreenRoute.ColorQuizResultScreen.withArgs(currentResult))
}

private fun NavHostController.navigateToWelcomeScreen() {
    navigate(ScreenRoute.WelcomeScreen.route) {
        popUpTo(ScreenRoute.WelcomeScreen.route) {
            inclusive = true
        }
    }
}