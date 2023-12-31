package com.theflankers.agrican.ui.screens.home.agricanservices.pests

import com.theflankers.agrican.domain.model.Pest
import com.theflankers.agrican.domain.repository.LogService
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PestsViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    logService: LogService
): BaseViewModel(logService) {

    private val _pests = MutableStateFlow<List<Pest>>(emptyList())
    val pests = _pests.asStateFlow()

    init {
        launchCatching {
            _pests.value = useCase.getPestsUseCase()
        }
    }
}