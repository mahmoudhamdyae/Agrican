package com.theflankers.agrican.ui.screens.home.main.problem_images

import android.content.Context
import android.net.Uri
import com.theflankers.agrican.common.ext.encodeImage
import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
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
            _uiState.value = _uiState.value.copy(crops = useCase.getCropsUseCase())
        }
    }

    fun updateUiState(
        image1: Uri? = _uiState.value.image1,
        image2: Uri? = _uiState.value.image2,
        image3: Uri? = _uiState.value.image3,
        selectedCrop: Crop = _uiState.value.selectedCrop,
        currentImage: Int = _uiState.value.currentImage
    ) {
        _uiState.value = _uiState.value.copy(
            image1 = image1,
            image2 = image2,
            image3 = image3,
            selectedCrop = selectedCrop,
            currentImage = currentImage
        )
    }

    fun delImage(imageNo: Int) {
        when (imageNo) {
            1 -> {
                _uiState.value = _uiState.value.copy(
                    image1 = _uiState.value.image2,
                    image2 = _uiState.value.image3,
                    image3 = null,
                    currentImage = _uiState.value.currentImage - 1
                )
            }
            2 -> {
                _uiState.value = _uiState.value.copy(
                    image2 = _uiState.value.image3,
                    image3 = null,
                    currentImage = _uiState.value.currentImage - 1
                )
            }
            else -> {
                _uiState.value = _uiState.value.copy(
                    image3 = null,
                    currentImage = _uiState.value.currentImage - 1
                )
            }
        }
    }

    fun search(context: Context) {
        launchCatching {
            useCase.searchUseCase(
                crop = _uiState.value.selectedCrop,
                image1 = _uiState.value.image1.encodeImage(context),
                image2 = _uiState.value.image2.encodeImage(context),
                image3 = _uiState.value.image3.encodeImage(context)
            )
        }
    }
}