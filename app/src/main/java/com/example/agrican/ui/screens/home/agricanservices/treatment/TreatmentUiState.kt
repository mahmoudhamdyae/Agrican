package com.example.agrican.ui.screens.home.agricanservices.treatment

import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.model.Treatment

data class TreatmentUiState(
    var crops: List<Crop> = emptyList(),
    var selectedCrop: Crop = Crop(),
    var diseaseType: String = "",
    var treatments: List<Treatment> = emptyList(),
)
