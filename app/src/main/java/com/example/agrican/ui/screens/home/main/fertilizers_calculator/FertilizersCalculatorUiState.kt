package com.example.agrican.ui.screens.home.main.fertilizers_calculator

import com.example.agrican.R
import com.example.agrican.domain.model.Crop

data class FertilizersCalculatorUiState(
    var crops: List<Crop> = emptyList(),
    var selectedCrop: Crop = Crop(),
    var measuringUnit: Int = R.string.hectare,
    var landSize: Int = 500000,
)
