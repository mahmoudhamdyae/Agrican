package com.example.agrican.ui.screens.home.agricanservices.order.new_order

import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewOrderViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {
    
    private val _uiState = MutableStateFlow(NewOrderUiState())
    val uiState = _uiState.asStateFlow()
    
    fun order(
        productName: String,
        quantity: String,
        receivingAddress: String,
        notes: String,
    ) {
        launchCatching { 
            useCase.orderNewProduct(
                productType= _uiState.value.productType,
                productName= productName,
                quantity = quantity.toInt(),
                quantityUnit = _uiState.value.quantityUnit,
                receivingAddress= receivingAddress,
                place= _uiState.value.place,
                city= _uiState.value.city,
                governorate= _uiState.value.governorate,
                notes= notes,
            )
        }
    }
}