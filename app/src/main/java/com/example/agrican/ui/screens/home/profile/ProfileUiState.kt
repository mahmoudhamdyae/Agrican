package com.example.agrican.ui.screens.home.profile

import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.model.Farm
import com.example.agrican.domain.model.User

data class ProfileUiState(
    var currentUser: User = User(),
    var farms: List<Farm> = emptyList(),
    var crops: List<Crop> = emptyList()
)
