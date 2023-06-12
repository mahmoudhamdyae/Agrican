package com.example.agrican.ui.screens.home.agricanservices.join_as_expert

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinAsExpertViewModel @Inject constructor(
    private val useCase: BaseUseCase
): ViewModel() {

    fun send(
        fullName: String,
        email: String,
        phoneNumber: String,
        image: Uri,
        navigateUp: () -> Unit
    ) {
        viewModelScope.launch {
            useCase.joinUsUseCase(
                userName = fullName,
                email = email,
                phoneNumber = phoneNumber,
                image = image
            )

            navigateUp()
        }
    }
}