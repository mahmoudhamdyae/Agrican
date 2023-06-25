package com.example.agrican.ui.screens.home.profile.add_crop

import com.example.agrican.R
import com.example.agrican.domain.model.Crop

data class AddCropUiState(
    val isLoading: Boolean = true,
    val crops: List<Crop>? = null,
    val selectedCrop: Crop = Crop(),
    val day: Int = R.string.day,
    val month: Int = R.string.month,
    val year: Int = R.string.year,
)
