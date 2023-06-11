package com.example.agrican.ui.screens.home.agricanservices.diseases

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.model.Disease
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiseaseViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _disease = MutableStateFlow(Disease())
    val disease = _disease.asStateFlow()

    init {
        val diseaseId: String = checkNotNull(savedStateHandle[DiseaseDestination.diseaseIdArg])
        viewModelScope.launch {
            _disease.value = useCase.getDisease(diseaseId)
        }
    }
}