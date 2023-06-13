package com.example.agrican.domain.use_case

import com.example.agrican.common.enums.SizeUnit
import com.example.agrican.domain.repository.MainRepository
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
        cropsType: String
    ) {

        val unit = when(sizeUnit) {
            SizeUnit.SQUARE_KILOMETER.title -> { SizeUnit.SQUARE_KILOMETER.name }
            else -> { SizeUnit.SQUARE_KILOMETER.name }
        }

        mainRepository.addFarm()
    }
}