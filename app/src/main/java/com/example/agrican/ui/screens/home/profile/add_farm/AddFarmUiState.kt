package com.example.agrican.ui.screens.home.profile.add_farm

import com.example.agrican.common.enums.SizeUnit

data class AddFarmUiState(
    val farmName: String = "",
    val farmSize: String = "",
    val sizeUnit: Int = SizeUnit.SQUARE_KILOMETER.title,
    val cropsType: String = "",
    val day: String = "",
    val month: String = "",
    val year: String = "",
)
