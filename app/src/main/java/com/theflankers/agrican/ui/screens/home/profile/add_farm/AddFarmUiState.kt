package com.theflankers.agrican.ui.screens.home.profile.add_farm

import com.theflankers.agrican.R
import com.theflankers.agrican.common.enums.SizeUnit

data class AddFarmUiState(
    val farmName: String = "",
    val farmSize: String = "",
    val sizeUnit: Int = SizeUnit.SQUARE_KILOMETER.title,
    val cropsType: String = "",
    val day: Int = R.string.day,
    val month: Int = R.string.month,
    val year: Int = R.string.year,
)
