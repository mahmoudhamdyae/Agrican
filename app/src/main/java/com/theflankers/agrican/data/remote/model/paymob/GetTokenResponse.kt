package com.theflankers.agrican.data.remote.model.paymob

data class GetTokenResponse(
    val profile: Profile,
    val token: String
)