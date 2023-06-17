package com.moya_utoma.color_data.data

import com.moya_utoma.color_data.data.model.NamedColor
import com.moya_utoma.quizer.Quizer
import com.moya_utoma.quizer.model.QuizState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ColorQuizer @Inject constructor() {
    private lateinit var quizer: Quizer

    private val _quizState = MutableStateFlow(QuizState())
    val quizState: StateFlow<QuizState> = _quizState.asStateFlow()

    var score = 0
        private set

    var answerCount = 0
        private set

    fun setupQuizer(colors: List<NamedColor>, scope: CoroutineScope) {
        quizer = Quizer(colors)
        startQuiz(scope)
    }

    fun checkAnswer(answer: String) {
        val isCorrect = quizer.checkAnswer(answer)
        answerCount++
        if (isCorrect) score++
    }

    private fun startQuiz(scope: CoroutineScope) {
        quizer.quizState.onEach {
            _quizState.value = it
        }.launchIn(scope)
    }
}