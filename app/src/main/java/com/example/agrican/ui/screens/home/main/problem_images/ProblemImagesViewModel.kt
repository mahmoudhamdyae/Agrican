package com.example.agrican.ui.screens.home.main.problem_images

import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProblemImagesViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private val _uiState = MutableStateFlow(ProblemImagesUiState())
    var uiState = _uiState.asStateFlow()

    init {
        launchCatching {
            _uiState.value.crops = useCase.getCropsUseCase()
        }
    }

    fun search() {
        launchCatching {
            useCase.searchUseCase(
                crop = _uiState.value.selectedCrop,
                image1 = _uiState.value.image1,
                image2 = _uiState.value.image2,
                image3 = _uiState.value.image3
            )
        }
    }
}