package com.example.agrican.domain.model

import com.example.agrican.R

data class User(
    val userName: String,
    val phoneNumber: String,
    val email: String,
    val userType: UserType,
    val image: String,
    val userId: String
)

enum class UserType(val title: Int) {
    FARM(R.string.farm),
    FARMER(R.string.farmer),
    ENGINEER(R.string.engineer)
}
