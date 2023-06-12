package com.example.agrican.domain.repository

interface AccountService {

    suspend fun login(userName: String, password: String)
    suspend fun signup(userName: String, email: String, password: String)
    suspend fun confirmSignUp(userName: String, code: String)
    suspend fun signOut()

    suspend fun resetPassword(userName: String)
    suspend fun confirmResetPassword(userName: String, newPassword: String, confirmationCode: String)

    suspend fun getCurrentUser(navigateToSignIn: () -> Unit)
}