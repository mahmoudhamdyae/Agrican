package com.example.agrican.domain.repository

interface AccountService {

    suspend fun hasUser(): Boolean

    suspend fun login(userName: String, password: String, onSuccess: () -> Unit)
    suspend fun signup(userName: String, email: String, phoneNumber: String, password: String, onSuccess: () -> Unit)
    suspend fun confirmSignUp(userName: String, code: String, onSuccess: () -> Unit)
    suspend fun signOut(onSuccess: () -> Unit)

    suspend fun resetPassword(userName: String, onSuccess: () -> Unit)
    suspend fun confirmResetPassword(userName: String, newPassword: String, confirmationCode: String, onSuccess: () -> Unit)

    suspend fun getCurrentUser(navigateToSignIn: () -> Unit)
}