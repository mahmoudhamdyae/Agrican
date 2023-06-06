package com.example.agrican.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.data.repository.PreferencesRepository
import com.example.agrican.ui.screens.auth.login.LoginDestination
import com.example.agrican.ui.screens.onboarding.OnboardingDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    preferencesRepository: PreferencesRepository,
): ViewModel() {

    private val isFirstTime: Flow<Boolean> =
        preferencesRepository.isFirstTime

    fun initialize(navigate: (String) -> Unit) {
//        if (no user) {
            viewModelScope.launch {
                isFirstTime.collect {
                    if (it) {
                        navigate(OnboardingDestination.route)
                        this.cancel()
                    } else {
                        navigate(LoginDestination.route)
                        this.cancel()
                    }
                }
            }
//        }
    }
}