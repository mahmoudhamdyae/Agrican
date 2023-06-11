package com.example.agrican.ui.screens.home.main.problem_images

import android.net.Uri
import com.example.agrican.domain.model.Crop

data class ProblemImagesUiState(
    var crops: List<Crop> = emptyList(),
    var selectedCrop: Crop = Crop(),
    var image1: Uri? = null,
    var image2: Uri? = null,
    var image3: Uri? = null,
)