package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetCropsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): List<Crop> {
        return listOf(
            Crop(name = "الأرز"),
            Crop(name = "نبات الصبار"),
            Crop(name = "نبات الياسمين"),
            Crop(name = "الأرز"),
            Crop(name = "الأرز"),
            Crop(name = "الأرز"),
        )
    }
}