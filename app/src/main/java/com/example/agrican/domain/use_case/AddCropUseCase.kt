package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class AddCropUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(
        crop: Crop,
        day: Int,
        month: Int,
        year: Int
    ) {
        mainRepository.addCrop()
    }
}