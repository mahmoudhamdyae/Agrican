package com.theflankers.agrican.ui.screens.home.agricanservices.treatment

import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.repository.LogService
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TreatmentViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    logService: LogService
): BaseViewModel(logService) {

    private val _uiState = MutableStateFlow(TreatmentUiState())
    val uiState = _uiState.asStateFlow()

    fun getCrops() {
        launchCatching {
            _uiState.value = _uiState.value.copy(
                crops = useCase.getCropsUseCase(),
                isLoading = false
            )
        }
    }

    fun getTreatments() {
        launchCatching {
            _uiState.value = _uiState.value.copy(isLoading = true)
            _uiState.value = _uiState.value.copy(
                treatments = useCase.getTreatmentsUseCase(
                    crop = _uiState.value.selectedCrop,
                    diseaseType = _uiState.value.diseaseType
                ),
                isLoading = false
            )
        }
    }

    fun onSelectCrop(crop: Crop) {
        _uiState.value = _uiState.value.copy(selectedCrop = crop)
    }

    fun updateDiseaseType(diseaseType: Int) {
        _uiState.value = _uiState.value.copy(diseaseType = diseaseType)
    }
}