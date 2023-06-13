package com.example.agrican.ui.screens.home.agricanservices.join_as_expert

import android.net.Uri
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JoinAsExpertViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    fun send(
        fullName: String,
        email: String,
        phoneNumber: String,
        image: Uri,
        navigateUp: () -> Unit
    ) {
        launchCatching {
            useCase.joinUsUseCase(
                fullName = fullName,
                email = email,
                phoneNumber = phoneNumber,
                image = image
            )

            navigateUp()
        }
    }
}