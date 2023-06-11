package com.example.agrican.ui.screens.home.agricanservices.pests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.model.Pest
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PestsViewModel @Inject constructor(
    private val useCase: BaseUseCase
): ViewModel() {

    private val _pests = MutableStateFlow<List<Pest>>(emptyList())
    val pests = _pests.asStateFlow()

    init {
        viewModelScope.launch {
            _pests.value = useCase.getPests()
        }
    }
}