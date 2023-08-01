package com.theflankers.agrican.domain.use_case.auth

import com.theflankers.agrican.domain.model.UserType
import com.theflankers.agrican.domain.repository.AccountService
import com.theflankers.agrican.domain.repository.MainRepository
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
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        accountService.signup(
            userName = userName,
            email = email,
            phoneNumber = phoneNumber,
            password = password,
            onSuccess = {

//                runBlocking {
//                    val userId = accountService.getCurrentUserId()
//                    val user = User(
//                        userName = userName,
//                        phoneNumber = phoneNumber,
//                        email = email,
//                        userType = accountType,
//                        userId = userId
//                    )
//
//                    mainRepository.createUser(user)
//                }

                onSuccess()
            },
            onError = onError
        )
    }
}