package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.common.enums.Quality
import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetDefaultAgeUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(
        day: Int,
        month: Int,
        year: Int,
        crop: Crop,
        currentQuality: Int,
    ): GetDefaultAgeResponse {

        val quality = when(currentQuality) {
            Quality.GOOD.title -> { Quality.GOOD.name }
            Quality.AVERAGE.title -> { Quality.GOOD.name }
            Quality.BELOW_AVERAGE.title -> { Quality.BELOW_AVERAGE.name }
            else -> { Quality.VERY_GOOD.name }
        }

//        return mainRepository.getDefaultAge()
        return GetDefaultAgeResponse(88f, 2, "باقى حوالى 21 يوم حتى تلف المحصول بالكامل\nالأفضل لك جمع المحصول خلال 6 أيام و استخدامه")
    }
}

data class GetDefaultAgeResponse(
    val defaultAge: Float,
    val dangerDegree: Int,
    val dangerAdvice: String,
)