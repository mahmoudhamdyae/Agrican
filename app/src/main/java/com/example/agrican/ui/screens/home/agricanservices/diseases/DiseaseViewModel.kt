package com.example.agrican.ui.screens.home.agricanservices.diseases

import androidx.lifecycle.SavedStateHandle
import com.example.agrican.domain.model.Disease
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DiseaseViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    savedStateHandle: SavedStateHandle,
): BaseViewModel() {

    private val _disease = MutableStateFlow(Disease())
    val disease = _disease.asStateFlow()

    init {
        val diseaseId: String = checkNotNull(savedStateHandle[DiseaseDestination.diseaseIdArg])
        launchCatching {
            _disease.value = useCase.getDisease(diseaseId)
        }
    }
}