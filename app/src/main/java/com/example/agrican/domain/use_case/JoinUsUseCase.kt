package com.example.agrican.domain.use_case

import android.net.Uri
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class JoinUsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(
        userName: String,
        email: String,
        phoneNumber: String,
        image: Uri
    ) {

        mainRepository.joinAsExpert()
    }
}