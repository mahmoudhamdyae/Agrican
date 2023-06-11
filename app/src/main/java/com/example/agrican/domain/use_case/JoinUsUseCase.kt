package com.example.agrican.domain.use_case

import android.net.Uri

class JoinUsUseCase {

    suspend operator fun invoke(
        userName: String,
        email: String,
        phoneNumber: String,
        image: Uri
    ) {
    }
}