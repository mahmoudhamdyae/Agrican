package com.example.agrican.ui.screens.notifications

import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
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