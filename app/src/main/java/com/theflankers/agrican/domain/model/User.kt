package com.theflankers.agrican.domain.model

import com.theflankers.agrican.R
import java.util.UUID

data class User(
    val userName: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val userType: UserType = UserType.ENGINEER,
    val image: String = "",
    val userId: String = UUID.randomUUID().toString()
)

enum class UserType(val title: Int) {
    FARM(R.string.farm),
    FARMER(R.string.farmer),
    ENGINEER(R.string.engineer)
}
