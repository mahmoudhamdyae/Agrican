package com.theflankers.agrican.ui.screens.home.main.fertilizers_calculator

import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.repository.LogService
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FertilizersCalculatorViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    logService: LogService
): BaseViewModel(logService) {

    private val _uiState = MutableStateFlow(FertilizersCalculatorUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launchCatching {
            _uiState.value = _uiState.value.copy(
                crops = useCase.getCropsUseCase(),
                isLoading = false
            )
        }
    }

    fun onSelectCrop(crop: Crop) {
        _uiState.value = _uiState.value.copy(selectedCrop = crop)
    }

    fun onMeasureUnitSelect(measuringUnit: Int) {
        _uiState.value = _uiState.value.copy(measuringUnit = measuringUnit)
    }

    fun decreaseSize() {
        _uiState.value = _uiState.value.copy(landSize = _uiState.value.landSize - 1)
    }

    fun increaseSize() {
        _uiState.value = _uiState.value.copy(landSize = _uiState.value.landSize + 1)
    }

    fun calculateFertilizers() {
        launchCatching {
            useCase.calculateFertilizersUseCase(
                crop = _uiState.value.selectedCrop,
                measuringUnit = _uiState.value.measuringUnit,
                landSize = _uiState.value.landSize,
            )
        }
    }
}