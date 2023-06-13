package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Message
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(message: Message) {
        mainRepository.sendMessage(message)
    }
}