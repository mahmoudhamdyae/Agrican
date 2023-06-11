package com.example.agrican.domain.use_case

import com.example.agrican.R
import com.example.agrican.domain.model.Crop

class CalculateFertilizersUseCase {

    suspend operator fun invoke(
        crop: Crop,
        measuringUnit: Int,
        landSize: Int,
    ) {
        val measuringUnitString = if (measuringUnit == R.string.hectare) "hectare" else "acre"
    }
}