package com.cherry.guessthething.presentation

import com.cherry.guessthething.domain.model.Cartoon

data class QuestionState(
    val rightAnswer: Cartoon,
    val variants: ArrayList<String>,
    var isAnswerRight: Boolean = true,
)
