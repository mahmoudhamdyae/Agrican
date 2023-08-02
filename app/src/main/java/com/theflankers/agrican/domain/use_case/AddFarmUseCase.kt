package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.common.enums.SizeUnit
import com.theflankers.agrican.domain.model.Farm
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class AddFarmUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(
        farmName: String,
        farmSize: String,
        sizeUnit: Int,
        day: Int,
        month: Int,
        year: Int,
        cropsType: String,
        farmAddress: String
    ) {

        val farm = Farm(name = farmName)

        val unit = when(sizeUnit) {
            SizeUnit.SQUARE_KILOMETER.title -> { SizeUnit.SQUARE_KILOMETER.name }
            SizeUnit.SQUARE_METER.title -> { SizeUnit.SQUARE_METER.name }
            SizeUnit.ACRE.title -> { SizeUnit.ACRE.name }
            SizeUnit.HECTARE.title -> { SizeUnit.HECTARE.name }
            else -> { SizeUnit.SQUARE_KILOMETER.name }
        }

        mainRepository.addFarm(farm)
    }
}