package com.example.agrican.ui.screens.home.agricanservices.pests

import androidx.lifecycle.SavedStateHandle
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
class PestViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _pest = MutableStateFlow(Pest())
    val pest = _pest.asStateFlow()

    init {
        val pestId: String = checkNotNull(savedStateHandle[PestDestination.pestIdArg])
        viewModelScope.launch {
            _pest.value = useCase.getPest(pestId)
        }
    }
}