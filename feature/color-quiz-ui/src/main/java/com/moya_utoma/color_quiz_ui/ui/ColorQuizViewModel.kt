package com.moya_utoma.color_quiz_ui.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moya_utoma.color_data.data.ColorQuizer
import com.moya_utoma.color_data.data.use_case.GetColorsUseCase
import com.moya_utoma.color_data.data.use_case.SaveColorQuizResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorQuizViewModel @Inject constructor(
    private val colorQuizer: ColorQuizer,
    private val getColors: GetColorsUseCase,
    private val saveColorQuizResult: SaveColorQuizResultUseCase
) : ViewModel() {

    val state = colorQuizer.quizState

    val score: Int
        get() = colorQuizer.score

    init {
        setupQuizer()
    }

    fun checkAnswer(answer: String) {
        colorQuizer.checkAnswer(answer)
    }

    fun saveResults() {
        viewModelScope.launch { saveColorQuizResult(colorQuizer.score) }
    }

    private fun setupQuizer() {
        viewModelScope.launch {
            val colors = getColors()
            colorQuizer.setupQuizer(colors, viewModelScope)

        }
    }
}