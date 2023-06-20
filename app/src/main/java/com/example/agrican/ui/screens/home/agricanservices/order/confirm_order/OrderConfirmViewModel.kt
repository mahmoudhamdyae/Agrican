package com.example.agrican.ui.screens.home.agricanservices.order.confirm_order

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.example.agrican.common.utils.Constant
import com.example.agrican.data.remote.PaymobApiService
import com.example.agrican.data.remote.model.ApiKeyModel
import com.example.agrican.data.remote.model.BillingData
import com.example.agrican.data.remote.model.OrderModel
import com.example.agrican.data.remote.model.PaymentRequest
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

enum class OrderWay {
    CASH, VISA
}

data class OrderRequest(
    val orderWay: OrderWay = OrderWay.CASH,
    val cardId: String = "",
    val expireDate: String = "",
    val cvc: String = "",
)

private const val TAG = "OrderConfirmViewModel"

@HiltViewModel
class OrderConfirmViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    private val apiService: PaymobApiService,
    savedStateHandle: SavedStateHandle,
): BaseViewModel() {

    private val _uiState = MutableStateFlow(OrderConfirmUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getToken()

        val orderId: String = checkNotNull(savedStateHandle[OrderConfirmDestination.orderIdArg])
        launchCatching {
            _uiState.value = _uiState.value.copy(
                order = useCase.getOrderUseCase(orderId),
                currentUser = useCase.getCurrentUserUseCase())
        }
    }

    private fun getToken() {
        launchCatching {
            try {
                val tokenResponse = apiService.getToken(ApiKeyModel(Constant.PAYMENT_KEY))

                if (tokenResponse.isSuccessful) {
                    Log.d(TAG, "getToken done ${tokenResponse.body()?.token}")
                    val token = tokenResponse.body()?.token

                    val orderResponse =  apiService.getOrder(
                        OrderModel(token.toString(), "false", _uiState.value.order.price.toString(), _uiState.value.order.currency)
                    )

                    if (orderResponse.isSuccessful) {
                        Log.d(TAG, "getOrder done ${orderResponse.body()?.id}")
                        val orderId = orderResponse.body()?.id

                        val requestModel = PaymentRequest(
                            _uiState.value.order.price.toString(), token.toString(),
                            BillingData(
                                "NA", "NA", "NA", "NA",
                                _uiState.value.currentUser.email, _uiState.value.currentUser.userName.trim(), "NA",
                                _uiState.value.currentUser.userName.trim(), _uiState.value.currentUser.phoneNumber, "NA", "NA",
                                "NA", "NA"
                            ), _uiState.value.order.currency, 3600, Constant.INTEGRATION_ID_CARD,
                            "true",
                            orderId.toString()
                        )

                        val paymentResponse = apiService.paymentRequest(requestModel)

                        if (paymentResponse.isSuccessful) {
                            Log.d(TAG, "paymentRequest done ${paymentResponse.body()?.token}")

                            val finalToken = paymentResponse.body()?.token
//                            Constant.finalToken = finalToken.toString()
                        }
                        else {
                            Log.d(
                                TAG, "paymentRequest error body ${paymentResponse.code()}" +
                                        " ${paymentResponse.message()}"
                            )
                        }
                    }
                    else {
                        Log.d(
                            TAG, "getOrder error body ${orderResponse.code()}" +
                                    " ${orderResponse.message()}"
                        )
                    }
                }
                else {
                    Log.d(
                        TAG,
                        "getToken error body ${tokenResponse.code()} ${tokenResponse.message()}"
                    )
                }

            }
            catch (e:Exception) {
                Log.d(TAG, "getToken error: ${e.message}")
            }
        }
    }

    fun buy(orderRequest: OrderRequest) {
    }
}