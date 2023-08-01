package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.repository.MainRepository
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
        mainRepository.addCrop(crop)
    }
}