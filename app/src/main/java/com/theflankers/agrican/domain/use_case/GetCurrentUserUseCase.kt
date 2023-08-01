package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.domain.model.User
import com.theflankers.agrican.domain.repository.AccountService
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val accountService: AccountService,
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(): User {
//        return mainRepository.getCurrentUser(accountService.getCurrentUserId())

        return User(
            userName = "Test Account",
            phoneNumber = "+86(8)9135210487",
            email = "claudette09@exa.com",
            userId = "2"
        )
    }
}