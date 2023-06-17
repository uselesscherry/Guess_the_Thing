package com.moya_utoma.navigation.nav_host

const val CURRENT_RESULT_KEY = "current_result"

sealed class ScreenRoute(val route: String) {
    object WelcomeScreen : ScreenRoute("welcome_screen")
    object ColorQuizScreen : ScreenRoute("color_quiz_screen")
    object ColorQuizResultScreen : ScreenRoute("color_quiz_result_screen/{$CURRENT_RESULT_KEY}") {
        fun withArgs(currentResult: Int) = route.replaceAfter("/", currentResult.toString())
    }
}
