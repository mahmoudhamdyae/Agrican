package com.theflankers.agrican.ui.screens.notifications

import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    init {
        launchCatching {
            // get Notifications
        }
    }
}