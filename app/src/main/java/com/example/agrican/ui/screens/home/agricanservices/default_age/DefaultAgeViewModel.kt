package com.example.agrican.ui.screens.home.agricanservices.default_age

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DefaultAgeViewModel @Inject constructor(
    private val useCase: BaseUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(DefaultAgeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value.crops = useCase.getCropsUseCase()
        }
    }

    fun getResults() {
        viewModelScope.launch {
             val defaultAgeResponse = useCase.getDefaultAgeUseCase(
                day = _uiState.value.day,
                month = _uiState.value.month,
                year = _uiState.value.year,
                crop = _uiState.value.currentCrop,
                currentQuality = _uiState.value.currentQuality
            )
            _uiState.value.defaultAge = defaultAgeResponse.first
            _uiState.value.dangerDegree = defaultAgeResponse.second
            _uiState.value.dangerAdvice = defaultAgeResponse.third
        }
    }
}