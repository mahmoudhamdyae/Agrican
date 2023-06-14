package com.example.agrican.domain.use_case.auth

import com.example.agrican.domain.model.User
import com.example.agrican.domain.model.UserType
import com.example.agrican.domain.repository.AccountService
import com.example.agrican.domain.repository.MainRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val accountService: AccountService,
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(
        userName: String,
        password: String,
        phoneNumber: String,
        email: String,
        accountType: UserType,
        onSuccess: () -> Unit
    ) {
        accountService.signup(userName, email, password, phoneNumber, onSuccess)

        val userId = accountService.getCurrentUserId()
        val user = User(
            userName = userName,
            phoneNumber = phoneNumber,
            email = email,
            userType = accountType,
            userId = userId
        )

        mainRepository.createUser(user)
    }
}