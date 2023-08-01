package com.theflankers.agrican.ui.screens.home.agricanservices.order.new_order

import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
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

    fun updateUiState(
        productType: Int = _uiState.value.productType,
        quantityUnit: Int = _uiState.value.quantityUnit,
        place: String = _uiState.value.place,
        city: String = _uiState.value.city,
        governorate: String = _uiState.value.governorate
    ) {
        _uiState.value = _uiState.value.copy(
            productType = productType,
            quantityUnit = quantityUnit,
            place = place,
            city = city,
            governorate = governorate
        )
    }
    
    fun order(
        productName: String,
        quantity: String,
        receivingAddress: String,
        notes: String,
    ) {
        launchCatching { 
            useCase.orderNewProductUseCase(
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