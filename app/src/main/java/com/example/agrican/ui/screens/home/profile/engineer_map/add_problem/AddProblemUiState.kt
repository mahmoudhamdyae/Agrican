package com.example.agrican.ui.screens.home.profile.engineer_map.add_problem

import com.example.agrican.R

data class AddProblemUiState(
    val problemKind: String = "",
    val problemDescription: String = "",
    val day: Int = R.string.day,
    val month: Int = R.string.month,
    val year: Int = R.string.year,
)