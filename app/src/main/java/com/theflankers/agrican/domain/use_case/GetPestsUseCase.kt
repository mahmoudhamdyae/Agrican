package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.domain.model.Pest
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetPestsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): List<Pest> {
//        return mainRepository.getPests()

        return listOf(
            Pest(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            ),
            Pest(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            ),
            Pest(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            ),
            Pest(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            ),
            Pest(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            )
        )
    }
}