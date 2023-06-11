package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Crop

class AddCropUseCase {

    suspend operator fun invoke(
        crop: Crop,
        day: String,
        month: String,
        year: String
    ) {
    }
}