package com.cherry.guessthething

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherry.guessthething.data.RepositoryImpl
import com.cherry.guessthething.model.Cartoon
import com.cherry.guessthething.view.QuestionState
import kotlinx.coroutines.launch
import kotlin.random.Random

class CartoonViewModel(
    application: Application,
    private val repository: RepositoryImpl = RepositoryImpl(application = application)
) : ViewModel() {

    var cartoons: List<Cartoon> = emptyList()
    val isLoaded: Boolean
        get() = cartoons.isNotEmpty()

    private var answerCount: Int = 0

    var rightAnswerCount: Int = 0
        private set

    val maxResult = repository.getMaxResult()

    companion object {
        const val normalModeTime = 30
        private const val buttonCount = 4
    }

    private lateinit var _state: MutableState<QuestionState>
    lateinit var state: State<QuestionState>

    init {
        Log.i("bebra", isLoaded.toString())
        viewModelScope.launch {
            repository.loadCartoonsList()
            cartoons = repository.getCartoons()
            playGame()
            Log.i("bebra", isLoaded.toString())
            _state = mutableStateOf(
                QuestionState(
                    rightAnswer = Setting.rightAnswer,
                    variants = Setting.variants
                )
            )
            state = _state
        }
    }

    private object Setting {
        lateinit var rightAnswer: Cartoon
        var rightAnswerIndex: Int = -1
        var variants = arrayListOf<String>()
    }

    private fun playGame() {
        Setting.variants = arrayListOf()
        generateAnswer()
        Setting.variants.add(Setting.rightAnswer.name)
        Setting.variants.addAll(generateWrongAnswer())
        Setting.variants.shuffle()
    }

    private fun generateAnswer() {
        var isFound = false
        while (!isFound) {
            val newIndex = Random.nextInt(cartoons.size)
            if (newIndex != Setting.rightAnswerIndex) {
                isFound = true
                Setting.rightAnswerIndex = newIndex
            }
        }
        Setting.rightAnswer = cartoons[Setting.rightAnswerIndex]
    }

    private fun generateWrongAnswer(): List<String> {
        var count = 0
        val listOfWrongAnswers = mutableListOf<String>()
        while (count != buttonCount - 1) {
            val tempWrongAnswer = cartoons[Random.nextInt(cartoons.size)].name
            if (tempWrongAnswer != Setting.rightAnswer.name && listOfWrongAnswers.all { it != tempWrongAnswer }) {
                listOfWrongAnswers.add(tempWrongAnswer)
                count++
            }
        }
        return listOfWrongAnswers
    }

    fun onClickEvent(answer: String) {
        val isRightAnswer = answer == _state.value.rightAnswer.name
        if (isRightAnswer) {
            rightAnswerCount++
        }
        answerCount++

        _state.value = state.value.copy(isAnswerRight = isRightAnswer)
        playGame()
        _state.value =
            state.value.copy(rightAnswer = Setting.rightAnswer, variants = Setting.variants)
    }

    suspend fun setMaxResult(currentResult: Int) {
        repository.saveMaxResult(currentResult)
    }

    fun clearCount() {
        answerCount = 0
        rightAnswerCount = 0
    }
}