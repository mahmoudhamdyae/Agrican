package com.theflankers.agrican.ui.screens.home.profile

import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.domain.model.Farm
import com.theflankers.agrican.domain.model.User

data class ProfileUiState(
    val currentUser: User = User(),
    val farms: List<Farm> = emptyList(),
    val crops: List<Crop> = emptyList()
)
