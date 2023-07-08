package com.example.agrican.ui.screens.home.profile.observe_crop

import com.example.agrican.domain.model.Crop

data class ObserveCropUiState(
    val crop: Crop = Crop(name = "الأرز", date = "30/05/2023"),
)
