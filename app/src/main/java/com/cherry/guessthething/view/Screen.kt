package com.cherry.guessthething.view

sealed class Screen(val route: String){
    object StartScreen:Screen("start_screen")
    object QuizScreen:Screen("quiz_screen")
}
