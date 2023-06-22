package com.example.agrican.ui.screens.home.agricanservices.pests

import com.example.agrican.domain.model.Pest
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PestsViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private val _pests = MutableStateFlow<List<Pest>>(emptyList())
    val pests = _pests.asStateFlow()

    init {
        launchCatching {
            _pests.value = useCase.getPestsUseCase()
        }
    }
}