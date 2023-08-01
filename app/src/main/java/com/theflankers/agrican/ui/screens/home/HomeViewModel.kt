package com.theflankers.agrican.ui.screens.home

import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
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

    fun signOut(navigate: () -> Unit) {
        launchCatching {
            _isLoading.value = true
            useCase.signOutUseCase(
                onSuccess = {
                    navigate()
                    _isLoading.value = false
                },
                onError = {
                    _isLoading.value = false
                }
            )
        }
    }
}