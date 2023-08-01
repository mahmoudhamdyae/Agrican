package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(
        crop: Crop,
        image1: String?,
        image2: String?,
        image3: String?,
    ) {
        mainRepository.searchProblem(
            crop = crop,
            image1 = image1,
            image2 = image2,
            image3 = image3
        )
    }
}