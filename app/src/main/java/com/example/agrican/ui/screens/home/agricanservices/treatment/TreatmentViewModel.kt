package com.example.agrican.ui.screens.home.agricanservices.treatment

import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TreatmentViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

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