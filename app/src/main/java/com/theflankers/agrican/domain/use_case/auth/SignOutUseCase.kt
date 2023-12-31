package com.theflankers.agrican.domain.use_case.auth

import com.theflankers.agrican.domain.repository.AccountService
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val accountService: AccountService
) {

    suspend operator fun invoke(onSuccess: () -> Unit, onError: () -> Unit) {
        accountService.signOut(onSuccess, onError)
    }
}