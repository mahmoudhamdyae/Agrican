package com.example.agrican.ui.screens.home

import com.example.agrican.data.repository.PreferencesRepository
import com.example.agrican.domain.repository.AccountService
import com.example.agrican.ui.screens.BaseViewModel
import com.example.agrican.ui.screens.auth.login.LoginDestination
import com.example.agrican.ui.screens.welcome.WelcomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository,
    private val accountService: AccountService
): BaseViewModel() {

    private val isFirstTime: Flow<Boolean> = preferencesRepository.isFirstTime

    fun initialize(navigate: (String) -> Unit) {

        launchCatching {
            isFirstTime.collectLatest {
                if (it) {
                    navigate(WelcomeDestination.route)
                    this.cancel()
                } else {
                    if (!accountService.hasUser()) {
                        navigate(LoginDestination.route)
                    }
                    this.cancel()
                }
            }
        }
    }
}