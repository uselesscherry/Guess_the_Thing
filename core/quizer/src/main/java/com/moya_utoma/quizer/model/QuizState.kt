package com.moya_utoma.quizer.model

data class QuizState(
    val isPreviousAnswerCorrect: Boolean = true,
    val question: QuizQuestion = QuizQuestion.EMPTY
)
