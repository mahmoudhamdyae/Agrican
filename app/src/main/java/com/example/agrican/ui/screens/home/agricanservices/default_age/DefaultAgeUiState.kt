package com.example.agrican.ui.screens.home.agricanservices.default_age

import com.example.agrican.domain.model.Crop

data class DefaultAgeUiState(
    var day: String = "",
    var month: String = "",
    var year: String = "",
    var crops: List<Crop> = emptyList(),
    var currentCrop: Crop = Crop(),
    var currentQuality: String = "",
    var defaultAge: Float? = null,
    var dangerDegree: Int? = null,
    var dangerAdvice: String = ""
)
