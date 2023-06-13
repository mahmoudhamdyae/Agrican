package com.example.agrican.ui.screens.home.agricanservices.default_age

import com.example.agrican.common.enums.Quality
import com.example.agrican.domain.model.Crop

data class DefaultAgeUiState(
    val day: Int = 0,
    val month: Int = 0,
    val year: Int = 0,
    val crops: List<Crop> = emptyList(),
    val currentCrop: Crop = Crop(),
    val currentQuality: Int = Quality.VERY_GOOD.title,
    val defaultAge: Float? = null,
    val dangerDegree: Int? = null,
    val dangerAdvice: String = ""
)
