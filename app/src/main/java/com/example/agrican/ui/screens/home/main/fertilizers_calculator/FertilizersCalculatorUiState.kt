package com.example.agrican.ui.screens.home.main.fertilizers_calculator

import com.example.agrican.R
import com.example.agrican.domain.model.Crop

data class FertilizersCalculatorUiState(
    val crops: List<Crop> = emptyList(),
    val selectedCrop: Crop = Crop(),
    val measuringUnit: Int = R.string.hectare,
    val landSize: Int = 500000,
)
