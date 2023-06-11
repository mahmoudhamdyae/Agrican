package com.example.agrican.ui.screens.home.agricanservices.order.new_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.use_case.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewOrderViewModel @Inject constructor(
    private val useCase: BaseUseCase
): ViewModel() {
    
    private val _uiState = MutableStateFlow(NewOrderUiState())
    val uiState = _uiState.asStateFlow()
    
    fun order() {
        viewModelScope.launch { 
            useCase.orderNewProduct(
                productType= _uiState.value.productType,
                productName= _uiState.value.productName,
                quantity = _uiState.value.quantity,
                quantityUnit = _uiState.value.quantityUnit,
                receivingAddress= _uiState.value.receivingAddress,
                place= _uiState.value.place,
                city= _uiState.value.city,
                governorate= _uiState.value.governorate,
                notes= _uiState.value.notes,
            )
        }
    }
}