package com.example.agrican.ui.screens.home

import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import com.example.agrican.ui.screens.auth.login.LoginDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun signOut(navigate: (String) -> Unit) {
        launchCatching {
            _isLoading.value = true
            useCase.signOutUseCase(
                onSuccess = {
                    navigate(LoginDestination.route)
                    _isLoading.value = false
                },
                onError = {
                    _isLoading.value = false
                }
            )
        }
    }
}