package com.example.agrican.ui.screens.home.agricanservices.default_age

import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DefaultAgeViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private val _uiState = MutableStateFlow(DefaultAgeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launchCatching {
            _uiState.value.crops = useCase.getCropsUseCase()
        }
    }

    fun getResults() {
        launchCatching {
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