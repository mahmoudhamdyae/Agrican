package com.example.agrican.ui.screens.home.profile.engineer_map.add_problem

import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddProblemViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    fun addProblem() {
        launchCatching {
        }
    }
}