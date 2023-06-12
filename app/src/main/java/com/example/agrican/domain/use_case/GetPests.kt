package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Pest
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetPests @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): List<Pest> {
        return listOf(
            Pest(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            )
        )
    }
}