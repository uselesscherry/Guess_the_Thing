package com.moya_utoma.quizer

import com.moya_utoma.quizer.model.QuizAnswer
import com.moya_utoma.quizer.model.QuizQuestion
import com.moya_utoma.quizer.model.QuizState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random
import kotlin.random.nextInt

private const val ANSWER_COUNT = 4

class Quizer(private val items: List<QuizAnswer>) {

    private val _quizState = MutableStateFlow(QuizState())
    val quizState: StateFlow<QuizState> = _quizState.asStateFlow()

    private fun startQuiz() {
        _quizState.update { it.copy(question = generateQuestion()) }
    }

    init {
        startQuiz()
    }

    fun checkAnswer(answer: String): Boolean {
        val isAnswerCorrect = answer == _quizState.value.question.rightAnswer.name

        generateQuestionState(isAnswerCorrect)
        return isAnswerCorrect
    }

    private fun generateQuestionState(wasLastAnswerCorrect: Boolean) {
        _quizState.value =
            QuizState(isPreviousAnswerCorrect = wasLastAnswerCorrect, question = generateQuestion())
    }

    private fun generateQuestion(): QuizQuestion {

        val currentAnswerIndex = items.indexOf(_quizState.value.question.rightAnswer)
        val newRightAnswer = items[generateDifferentRandomNumber(currentAnswerIndex, items.indices)]

        val variants = generateVariants(
            rightAnswer = newRightAnswer.name,
            wrongAnswers = generateWrongAnswers(newRightAnswer.name)
        )
        return QuizQuestion(newRightAnswer, variants)
    }

    private fun generateDifferentRandomNumber(currentNumber: Int, range: IntRange): Int {
        val randomNumber = Random.nextInt(range)

        return if (randomNumber == currentNumber) {
            generateDifferentRandomNumber(currentNumber, range)
        } else {
            randomNumber
        }
    }

    private fun generateVariants(rightAnswer: String, wrongAnswers: List<String>): List<String> {
        val variants = mutableListOf<String>()
        variants.add(rightAnswer)
        variants.addAll(wrongAnswers)
        variants.shuffle()
        return variants
    }

    private fun generateWrongAnswers(rightAnswer: String): List<String> {
        var count = 0
        val listOfWrongAnswers = mutableListOf<String>()
        while (count != ANSWER_COUNT - 1) {
            val tempWrongAnswer = items[Random.nextInt(items.size)].name
            val isNotDuplicated =
                tempWrongAnswer != rightAnswer && listOfWrongAnswers.all { it != tempWrongAnswer }
            if (isNotDuplicated) {
                listOfWrongAnswers.add(tempWrongAnswer)
                count++
            }
        }
        return listOfWrongAnswers
    }

}