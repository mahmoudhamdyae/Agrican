package com.example.agrican.domain.use_case

import android.net.Uri
import androidx.core.net.toFile
import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.repository.MainRepository
import java.io.File
import java.net.URI
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
        mainRepository.searchProblem(
            crop = crop,
            image1 = image1?.toFile() ?: File(URI("")),
            image2 = image2?.toFile() ?: File(URI("")),
            image3 = image3?.toFile() ?: File(URI(""))
        )
    }
}