package com.example.agrican.data.repository

interface AccountService {

    suspend fun login()
    suspend fun signup()
    suspend fun signOut()
    suspend fun forgotPassword()

    suspend fun getCurrentUser()
}

class AccountServiceImpl {
}