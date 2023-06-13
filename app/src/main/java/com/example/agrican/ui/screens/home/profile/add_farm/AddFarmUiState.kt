package com.example.agrican.ui.screens.home.profile.add_farm

import com.example.agrican.R
import com.example.agrican.common.enums.SizeUnit

data class AddFarmUiState(
    val farmName: String = "",
    val farmSize: String = "",
    val sizeUnit: Int = SizeUnit.SQUARE_KILOMETER.title,
    val cropsType: String = "",
    val day: Int = R.string.day,
    val month: Int = R.string.month,
    val year: Int = R.string.year,
)
