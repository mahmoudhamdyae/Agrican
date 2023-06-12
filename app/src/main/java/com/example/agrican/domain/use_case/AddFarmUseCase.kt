package com.example.agrican.domain.use_case

import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class AddFarmUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(
        farmName: String,
        farmSize: String,
        sizeUnit: String,
        day: String,
        month: String,
        year: String,
        cropsType: String
    ) {
        mainRepository.addFarm()
    }
}