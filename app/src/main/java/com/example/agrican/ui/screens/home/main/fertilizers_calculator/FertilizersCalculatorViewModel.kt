package com.example.agrican.ui.screens.home.main.fertilizers_calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FertilizersCalculatorViewModel @Inject constructor(
    private val useCase: BaseUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(FertilizersCalculatorUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value.crops = useCase.getCropsUseCase()
        }
    }

    fun calculateFertilizers() {
        viewModelScope.launch {
            useCase.calculateFertilizersUseCase(
                crop = _uiState.value.selectedCrop,
                measuringUnit = _uiState.value.measuringUnit,
                landSize = _uiState.value.landSize
            )
        }
    }
}