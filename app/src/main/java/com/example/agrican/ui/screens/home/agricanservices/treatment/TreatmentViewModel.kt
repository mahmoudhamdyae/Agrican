package com.example.agrican.ui.screens.home.agricanservices.treatment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreatmentViewModel @Inject constructor(
    private val useCase: BaseUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(TreatmentUiState())
    val uiState = _uiState.asStateFlow()

    fun getCrops() {
        viewModelScope.launch {
            _uiState.value.crops = useCase.getCropsUseCase()
        }
    }

    fun getTreatments() {
        viewModelScope.launch {
            _uiState.value.treatments = useCase.getTreatmentsUseCase(
                crop = _uiState.value.selectedCrop,
                diseaseType = _uiState.value.diseaseType
            )
        }
    }
}