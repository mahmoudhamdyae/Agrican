package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetCropsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): List<Crop> {
//        return mainRepository.getCrops()

        return listOf(
            Crop(cropId = "11", name = "الأرز"),
            Crop(cropId = "12", name = "نبات الصبار"),
            Crop(cropId = "13", name = "نبات الياسمين"),
            Crop(cropId = "14", name = "الأرز"),
            Crop(cropId = "15", name = "الأرز"),
            Crop(cropId = "16", name = "الأرز"),
        )
    }
}