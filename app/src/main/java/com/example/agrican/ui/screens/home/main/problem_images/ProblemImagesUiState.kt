package com.example.agrican.ui.screens.home.main.problem_images

import android.net.Uri
import com.example.agrican.domain.model.Crop

data class ProblemImagesUiState(
    val crops: List<Crop> = emptyList(),
    val selectedCrop: Crop = Crop(),
    val image1: Uri? = null,
    val image2: Uri? = null,
    val image3: Uri? = null,
)