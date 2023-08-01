package com.theflankers.agrican.ui.screens.home.agricanservices.default_age

import com.theflankers.agrican.R
import com.theflankers.agrican.common.enums.Quality
import com.theflankers.agrican.domain.model.Crop

data class DefaultAgeUiState(
    val isLoading: Boolean = true,
    val day: Int = R.string.day,
    val month: Int = R.string.month,
    val year: Int = R.string.year,
    val crops: List<Crop>? = null,
    val currentCrop: Crop = Crop(),
    val currentQuality: Int = Quality.VERY_GOOD.title,
    val defaultAge: Float? = null,
    val dangerDegree: Int? = null,
    val dangerAdvice: String = ""
)
