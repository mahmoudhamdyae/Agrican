package com.example.agrican.ui.screens.splash

import com.example.agrican.data.repository.PreferencesRepository
import com.example.agrican.domain.repository.AccountService
import com.example.agrican.ui.screens.BaseViewModel
import com.example.agrican.ui.screens.auth.login.LoginDestination
import com.example.agrican.ui.screens.home.HomeDestination
import com.example.agrican.ui.screens.welcome.WelcomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository,
    private val accountService: AccountService
): BaseViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launchCatching {
            preferencesRepository.isFirstTime.collectLatest {
                if (it) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        startDestination = WelcomeDestination.route
                    )
                    this.cancel()
                } else {
                    if (!accountService.hasUser()) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            startDestination = LoginDestination.route
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            startDestination = HomeDestination.route
                        )
                    }
                    this.cancel()
                }
            }
        }
    }
}