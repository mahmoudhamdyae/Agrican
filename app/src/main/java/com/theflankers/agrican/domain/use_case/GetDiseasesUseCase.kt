package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.domain.model.Disease
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetDiseasesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): List<Disease> {
//        return mainRepository.getDiseases()

        return listOf(
            Disease(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            ),
            Disease(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            ),
            Disease(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            ),
            Disease(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            ),
            Disease(
                title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى"
            )
        )
    }
}