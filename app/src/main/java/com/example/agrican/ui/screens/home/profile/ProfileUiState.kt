package com.example.agrican.ui.screens.home.profile

import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.model.Farm
import com.example.agrican.domain.model.User

data class ProfileUiState(
    val currentUser: User = User(),
    val farms: List<Farm> = emptyList(),
    val crops: List<Crop> = emptyList()
)
