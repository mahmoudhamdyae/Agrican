package com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_progress

import com.theflankers.agrican.R

data class AddProgressUiState(
    val progress: String = "",
    val day: Int = R.string.day,
    val month: Int = R.string.month,
    val year: Int = R.string.year,
)