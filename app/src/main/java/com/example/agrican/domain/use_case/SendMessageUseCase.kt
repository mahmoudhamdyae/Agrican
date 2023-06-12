package com.example.agrican.domain.use_case

import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(messageBody: String) {
        mainRepository.sendMessage()
    }
}