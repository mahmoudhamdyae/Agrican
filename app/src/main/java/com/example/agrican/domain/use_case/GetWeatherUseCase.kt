package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Weather

class GetWeatherUseCase {

    suspend operator fun invoke(): Weather {
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