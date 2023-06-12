package com.example.agrican.ui.screens.home

import com.example.agrican.data.repository.PreferencesRepository
import com.example.agrican.domain.repository.AccountService
import com.example.agrican.ui.screens.BaseViewModel
import com.example.agrican.ui.screens.auth.login.LoginDestination
import com.example.agrican.ui.screens.welcome.WelcomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository,
    // todo del this
    private val accountService: AccountService
): BaseViewModel() {

    private val isFirstTime: Flow<Boolean> = preferencesRepository.isFirstTime

    fun initialize(navigate: (String) -> Unit) {

        launchCatching {
            accountService.getCurrentUser(navigateToSignIn = {
                launchCatching {
                    isFirstTime.collect {
                        if (it) {
                            navigate(WelcomeDestination.route)
                            this.cancel()
                        } else {
                            navigate(LoginDestination.route)
                            this.cancel()
                        }
                    }
                }
            })
        }
    }
}