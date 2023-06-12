package com.example.agrican.ui.screens.home.agricanservices.treatment

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
            _uiState.value.crops = useCase.getCropsUseCase()
        }
    }

    fun getTreatments() {
        launchCatching {
            _uiState.value.treatments = useCase.getTreatmentsUseCase(
                crop = _uiState.value.selectedCrop,
                diseaseType = _uiState.value.diseaseType
            )
        }
    }
}