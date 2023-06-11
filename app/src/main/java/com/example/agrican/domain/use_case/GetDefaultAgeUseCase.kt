package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Crop

class GetDefaultAgeUseCase {

    suspend operator fun invoke(
        day: String = "",
        month: String = "",
        year: String = "",
        crop: Crop,
        currentQuality: String,
    ): Triple<Float, Int, String> {
        return Triple(88f, 2, "باقى حوالى 21 يوم حتى تلف المحصول بالكامل\nالأفضل لك جمع المحصول خلال 6 أيام و استخدامه")
    }
}