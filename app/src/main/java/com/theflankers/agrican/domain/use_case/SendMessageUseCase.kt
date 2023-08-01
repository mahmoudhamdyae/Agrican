package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.domain.model.Message
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(message: Message) {
        mainRepository.sendMessage(message)
    }
}