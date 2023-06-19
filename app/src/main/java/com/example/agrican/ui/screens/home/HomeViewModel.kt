package com.example.agrican.ui.screens.home

import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import com.example.agrican.ui.screens.auth.login.LoginDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    fun signOut(navigate: (String) -> Unit) {
        launchCatching {
            useCase.signOutUseCase { navigate(LoginDestination.route) }
        }
    }
}