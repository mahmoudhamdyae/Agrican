package com.theflankers.agrican.ui.screens.home.main.problem_images

import android.net.Uri
import com.theflankers.agrican.domain.model.Crop

data class ProblemImagesUiState(
    val isLoading: Boolean = true,
    val crops: List<Crop>? = null,
    val selectedCrop: Crop = Crop(),
    val image1: Uri? = null,
    val image2: Uri? = null,
    val image3: Uri? = null,
    val currentImage: Int = 0
)