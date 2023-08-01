package com.theflankers.agrican.ui.screens.home.agricanservices.treatment

import com.theflankers.agrican.common.enums.DiseaseType
import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.model.Treatment

data class TreatmentUiState(
    val isLoading: Boolean = true,
    val crops: List<Crop>? = null,
    val selectedCrop: Crop = Crop(),
    val diseaseType: Int = DiseaseType.INSECTS.title,
    val treatments: List<Treatment> = emptyList(),
)
