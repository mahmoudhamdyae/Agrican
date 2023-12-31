package com.theflankers.agrican.ui.screens.home.agricanservices.pests

import androidx.lifecycle.SavedStateHandle
import com.theflankers.agrican.domain.model.Pest
import com.theflankers.agrican.domain.repository.LogService
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PestViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    savedStateHandle: SavedStateHandle,
    logService: LogService
): BaseViewModel(logService) {

    private val _pest = MutableStateFlow(Pest())
    val pest = _pest.asStateFlow()

    init {
        val pestId: String = checkNotNull(savedStateHandle[PestDestination.pestIdArg])
        launchCatching {
            _pest.value = useCase.getPestUseCase(pestId)
        }
    }
}