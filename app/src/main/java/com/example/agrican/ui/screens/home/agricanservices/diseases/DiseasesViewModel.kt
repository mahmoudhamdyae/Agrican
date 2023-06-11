package com.example.agrican.ui.screens.home.agricanservices.diseases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.model.Disease
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiseasesViewModel @Inject constructor(
    private val useCase: BaseUseCase
): ViewModel() {

    private val _diseases = MutableStateFlow<List<Disease>>(emptyList())
    val diseases = _diseases.asStateFlow()

    init {
        viewModelScope.launch {
            _diseases.value = useCase.getDiseases()
        }
    }
}