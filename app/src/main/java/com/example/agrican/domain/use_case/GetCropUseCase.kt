package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetCropUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(cropId: String): Crop {
//        return mainRepository.getCrop(cropId)
        return Crop(name = "الأرز", date = "30/05/2023")
    }
}