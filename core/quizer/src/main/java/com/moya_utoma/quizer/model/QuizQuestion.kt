package com.moya_utoma.quizer.model

data class QuizQuestion(
    val rightAnswer: QuizAnswer,
    val variants: List<String>
) {
    companion object {
        val EMPTY = QuizQuestion(
            object : QuizAnswer() {
                override val description: String = ""
                override val name: String = ""
            }, emptyList()
        )
    }

    val isNotEmpty: Boolean
        get() = this != EMPTY
}