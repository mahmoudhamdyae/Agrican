package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Pest

class GetPests {

    suspend operator fun invoke(): List<Pest> {
        return listOf(
            Pest(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            )
        )
    }
}