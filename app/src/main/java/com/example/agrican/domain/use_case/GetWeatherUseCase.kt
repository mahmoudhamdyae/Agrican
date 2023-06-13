package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Weather
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): Weather {
//        return mainRepository.getWeather()

        return Weather(
            air = "مقبول",
            degree = 31.0,
            weatherDescription = "مشمس",
            wind = "جنوبية غربية 33 كم/س",
            windGusts = "47 كم/س",
            firstInformation = "جو مناسب لرى نبات العنب",
            secondInformation = "من المتوقع حدوث عواصف شديدة غدا"
        )
    }
}