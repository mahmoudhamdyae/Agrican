package com.theflankers.agrican.ui.screens.home.profile.engineer_map.add_map

import com.theflankers.agrican.domain.repository.LogService
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddMapViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    logService: LogService
): BaseViewModel(logService) {

    fun addMap() {
        launchCatching {
        }
    }
}