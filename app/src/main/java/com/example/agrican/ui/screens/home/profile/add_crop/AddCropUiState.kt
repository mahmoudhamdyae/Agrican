package com.example.agrican.ui.screens.home.profile.add_crop

import com.example.agrican.domain.model.Crop

data class AddCropUiState(
    var crops: List<Crop> = emptyList(),
    var selectedCrop: Crop = Crop(),
    var day: String = "",
    var month: String = "",
    var year: String = "",
)
