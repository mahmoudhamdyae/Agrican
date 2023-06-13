package com.example.agrican.domain.use_case

import com.example.agrican.common.enums.MeasureUnit
import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class CalculateFertilizersUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(
        crop: Crop,
        measuringUnit: Int,
        landSize: Int,
    ) {
        val measuringUnitString =
            if (measuringUnit == MeasureUnit.HECTARE.title) MeasureUnit.HECTARE.name
            else MeasureUnit.ACRE.name

        mainRepository.calculateFertilize()
    }
}