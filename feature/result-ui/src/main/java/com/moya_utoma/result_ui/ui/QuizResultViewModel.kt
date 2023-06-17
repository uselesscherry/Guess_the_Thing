package com.moya_utoma.result_ui.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moya_utoma.color_data.data.use_case.GetColorQuizMaxResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuizResultViewModel @Inject constructor(
    getColorQuizMaxResult: GetColorQuizMaxResultUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizResultState())
    val uiState = _uiState.asStateFlow()

    private val colorQuizMaxResult = getColorQuizMaxResult().onEach { maxResult ->
        _uiState.update { it.copy(maxResult = maxResult) }
    }

    init {
        colorQuizMaxResult.launchIn(viewModelScope)
    }
}