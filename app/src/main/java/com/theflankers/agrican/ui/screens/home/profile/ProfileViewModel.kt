package com.theflankers.agrican.ui.screens.home.profile

import com.theflankers.agrican.domain.repository.LogService
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    logService: LogService
): BaseViewModel(logService) {

    private var _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launchCatching {
            _uiState.value = _uiState.value.copy(
                currentUser = useCase.getCurrentUserUseCase(),
                crops = useCase.getCropsUseCase(),
                farms = useCase.getFarmsUseCase()
            )
        }
    }

    fun delFarm() {
        launchCatching {
        }
    }

    fun delCrop() {
        launchCatching {
        }
    }
}