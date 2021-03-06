package com.cherry.guessthething.presentation

const val CURRENT_RESULT_KEY = "current_result"

sealed class Screen(val route: String) {
    object StartScreen : Screen("start_screen")
    object QuizScreen : Screen("quiz_screen")
    object QuizResultScreen : Screen("quiz_result_screen/{$CURRENT_RESULT_KEY}") {
        fun withArgs(currentResult: Int) = route.replaceAfter("/", currentResult.toString())
    }
}
