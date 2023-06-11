package com.example.agrican.domain.use_case

import android.net.Uri
import com.example.agrican.domain.model.Crop

class SearchUseCase {

    suspend operator fun invoke(
        crop: Crop,
        image1: Uri?,
        image2: Uri?,
        image3: Uri?,
    ) {
    }
}