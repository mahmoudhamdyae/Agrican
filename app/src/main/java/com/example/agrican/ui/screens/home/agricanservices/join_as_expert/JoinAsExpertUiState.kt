package com.example.agrican.ui.screens.home.agricanservices.join_as_expert

import android.net.Uri

data class JoinAsExpertUiState(
    var userName: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var image: Uri = Uri.EMPTY
)
