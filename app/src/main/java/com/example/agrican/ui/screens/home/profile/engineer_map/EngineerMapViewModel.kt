package com.example.agrican.ui.screens.home.profile.engineer_map

import com.example.agrican.domain.model.Farm
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EngineerMapViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private val _farms = MutableStateFlow(listOf<Farm>())
    val farms = _farms.asStateFlow()

    init {
        launchCatching {
            _farms.value = useCase.getFarmsUseCase()
        }
    }
}