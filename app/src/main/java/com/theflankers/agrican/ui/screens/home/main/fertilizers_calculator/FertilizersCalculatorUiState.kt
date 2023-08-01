package com.theflankers.agrican.ui.screens.home.main.fertilizers_calculator

import com.theflankers.agrican.R
import com.theflankers.agrican.domain.model.Crop

data class FertilizersCalculatorUiState(
    val isLoading: Boolean = true,
    val crops: List<Crop>? = null,
    val selectedCrop: Crop = Crop(),
    val measuringUnit: Int = R.string.hectare,
    val landSize: Int = 500000,
)
