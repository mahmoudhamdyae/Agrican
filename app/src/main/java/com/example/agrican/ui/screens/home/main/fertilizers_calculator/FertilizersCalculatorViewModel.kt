package com.example.agrican.ui.screens.home.main.fertilizers_calculator

import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FertilizersCalculatorViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private val _uiState = MutableStateFlow(FertilizersCalculatorUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launchCatching {
            _uiState.value.crops = useCase.getCropsUseCase()
        }
    }

    fun calculateFertilizers() {
        launchCatching {
            useCase.calculateFertilizersUseCase(
                crop = _uiState.value.selectedCrop,
                measuringUnit = _uiState.value.measuringUnit,
                landSize = _uiState.value.landSize
            )
        }
    }
}