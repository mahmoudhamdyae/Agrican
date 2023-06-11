package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.model.Treatment

class GetTreatmentsUseCase {

    suspend operator fun invoke(crop: Crop, diseaseType: String): List<Treatment> {
        return listOf(
            Treatment(
                name = "علاج 1",
                description = "هذه هى الكمية المطلوبة بناء و يتم تسميد الأرض باستخدام المنتجات المخصصة لذلك هذه هى الكمية المطلوبة بناء و يتم تسميد الأرض باستخدام المنتجات المخصصة لذلك"
            )
        )
    }
}