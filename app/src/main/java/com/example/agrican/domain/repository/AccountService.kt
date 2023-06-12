package com.example.agrican.domain.repository

interface AccountService {

    suspend fun login(userName: String, password: String)
    suspend fun signup(userName: String, email: String, password: String)
    suspend fun signOut()
    suspend fun forgotPassword()

    suspend fun getCurrentUser()
}