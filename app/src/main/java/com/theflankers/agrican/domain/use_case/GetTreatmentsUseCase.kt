package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.common.enums.DiseaseType
import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.model.Treatment
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetTreatmentsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(crop: Crop, diseaseType: Int): List<Treatment> {

        val disease = when(diseaseType) {
            DiseaseType.INSECTS.title -> { DiseaseType.INSECTS }
            else -> { DiseaseType.INSECTS }
        }

//        return mainRepository.getTreatments()

        return listOf(
            Treatment(
                name = "علاج 1",
                description = "هذه هى الكمية المطلوبة بناء و يتم تسميد الأرض باستخدام المنتجات المخصصة لذلك هذه هى الكمية المطلوبة بناء و يتم تسميد الأرض باستخدام المنتجات المخصصة لذلك"
            )
        )
    }
}