package com.example.agrican.domain.use_case

import android.net.Uri
import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(
        crop: Crop,
        image1: Uri?,
        image2: Uri?,
        image3: Uri?,
    ) {
        mainRepository.searchProblem()
    }
}