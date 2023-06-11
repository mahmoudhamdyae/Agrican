package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Crop

class GetCropsUseCase {

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