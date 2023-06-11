package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Disease

class GetDiseases {

    suspend operator fun invoke(): List<Disease> {
        return listOf(
            Disease(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            )
        )
    }
}