package com.example.agrican.ui.screens.home.agricanservices.treatment

import com.example.agrican.common.enums.DiseaseType
import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.model.Treatment

data class TreatmentUiState(
    val isLoading: Boolean = true,
    val crops: List<Crop>? = null,
    val selectedCrop: Crop = Crop(),
    val diseaseType: Int = DiseaseType.INSECTS.title,
    val treatments: List<Treatment> = emptyList(),
)
