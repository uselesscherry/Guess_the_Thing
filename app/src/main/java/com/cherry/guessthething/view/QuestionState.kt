package com.cherry.guessthething.view

import com.cherry.guessthething.model.Cartoon

data class QuestionState(
    val rightAnswer:Cartoon,
    val variants: ArrayList<String>,
    var isAnswerRight: Boolean = true,
)
