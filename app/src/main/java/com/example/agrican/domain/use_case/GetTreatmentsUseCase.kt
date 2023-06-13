package com.example.agrican.domain.use_case

import com.example.agrican.common.enums.DiseaseType
import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.model.Treatment
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetTreatmentsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(crop: Crop, diseaseType: Int): List<Treatment> {

        val disease = when(diseaseType) {
            DiseaseType.INSECTS.title -> { DiseaseType.INSECTS }
            else -> { DiseaseType.INSECTS }
        }

        return listOf(
            Treatment(
                name = "علاج 1",
                description = "هذه هى الكمية المطلوبة بناء و يتم تسميد الأرض باستخدام المنتجات المخصصة لذلك هذه هى الكمية المطلوبة بناء و يتم تسميد الأرض باستخدام المنتجات المخصصة لذلك"
            )
        )
    }
}