package com.example.agrican.ui.screens.home.agricanservices.diseases

import com.example.agrican.domain.model.Disease
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DiseasesViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private val _diseases = MutableStateFlow<List<Disease>>(emptyList())
    val diseases = _diseases.asStateFlow()

    init {
        launchCatching {
            _diseases.value = useCase.getDiseases()
        }
    }
}