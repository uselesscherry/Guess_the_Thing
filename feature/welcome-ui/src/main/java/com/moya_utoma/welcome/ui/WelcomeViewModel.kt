package com.moya_utoma.welcome.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moya_utoma.color_data.data.use_case.DownloadColorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val downloadColorsUseCase: DownloadColorsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WelcomeState())
    val uiState = _uiState.asStateFlow()

    init {
        downloadColors()
    }

    private fun downloadColors() {
        viewModelScope.launch {
            downloadColorsUseCase()
            _uiState.update { it.copy(isColorDataLoaded = true) }
        }
    }

}