package com.example.agrican.ui.screens.home.agricanservices.pests

import androidx.lifecycle.SavedStateHandle
import com.example.agrican.domain.model.Pest
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PestViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    savedStateHandle: SavedStateHandle,
): BaseViewModel() {

    private val _pest = MutableStateFlow(Pest())
    val pest = _pest.asStateFlow()

    init {
        val pestId: String = checkNotNull(savedStateHandle[PestDestination.pestIdArg])
        launchCatching {
            _pest.value = useCase.getPest(pestId)
        }
    }
}