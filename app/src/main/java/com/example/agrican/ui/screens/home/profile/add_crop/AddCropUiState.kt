package com.example.agrican.ui.screens.home.profile.add_crop

import com.example.agrican.domain.model.Crop

data class AddCropUiState(
    val crops: List<Crop> = emptyList(),
    val selectedCrop: Crop = Crop(),
    val day: String = "",
    val month: String = "",
    val year: String = "",
)
