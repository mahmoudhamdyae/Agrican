package com.example.agrican.domain.use_case

class AddFarmUseCase {

    suspend operator fun invoke(
        farmName: String,
        farmSize: String,
        sizeUnit: String,
        day: String,
        month: String,
        year: String,
        cropsType: String
    ) {
    }
}