package com.theflankers.agrican.ui.screens.home.agricanservices.order.order_status

import android.content.Context
import android.content.Intent
import android.util.Log
import com.theflankers.agrican.common.utils.Constant
import com.theflankers.agrican.data.remote.PaymobApiService
import com.theflankers.agrican.data.remote.model.paymob.ApiKeyModel
import com.theflankers.agrican.data.remote.model.paymob.BillingData
import com.theflankers.agrican.data.remote.model.paymob.OrderModel
import com.theflankers.agrican.data.remote.model.paymob.PaymentRequest
import com.theflankers.agrican.domain.model.Order
import com.theflankers.agrican.domain.repository.LogService
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import com.theflankers.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val TAG = "OrderViewModel"

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    private val apiService: PaymobApiService,
    logService: LogService
): BaseViewModel(logService) {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launchCatching {
            _uiState.value = _uiState.value.copy(
                orders = useCase.getOrdersUseCase(),
                currentUser = useCase.getCurrentUserUseCase(),
                isLoading = false
            )
        }
    }

    fun getTokenAndConfirmOrder(context: Context, order: Order, launchPayment: (Intent) -> Unit) {
        launchCatching {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val tokenResponse = apiService.getToken(ApiKeyModel(Constant.PAYMENT_KEY))

                if (tokenResponse.isSuccessful) {
                    Log.d(TAG, "getToken done ${tokenResponse.body()?.token}")
                    val token = tokenResponse.body()?.token

                    val orderResponse =  apiService.getOrder(
                        OrderModel(token.toString(), "false", order.price.toString(), order.currency)
                    )

                    if (orderResponse.isSuccessful) {
                        Log.d(TAG, "getOrder done ${orderResponse.body()?.id}")
                        val orderId = orderResponse.body()?.id

                        val requestModel = PaymentRequest(
                            order.price.toString(), token.toString(),
                            BillingData(
                                "NA", "NA", "NA", "NA",
                                _uiState.value.currentUser.email, _uiState.value.currentUser.userName.trim(), "NA",
                                _uiState.value.currentUser.userName.trim(), _uiState.value.currentUser.phoneNumber, "NA", "NA",
                                "NA", "NA"
                            ), order.currency, 3600, Constant.INTEGRATION_ID_CARD,
                            "true",
                            orderId.toString()
                        )

                        val paymentResponse = apiService.paymentRequest(requestModel)

                        if (paymentResponse.isSuccessful) {
                            Log.d(TAG, "paymentRequest done ${paymentResponse.body()?.token}")

                            val finalToken = paymentResponse.body()?.token
                            _uiState.value = _uiState.value.copy(token = finalToken)
                            confirmOrder(context, order, launchPayment)
                        }
                        else {
                            _uiState.value = _uiState.value.copy(isLoading = false)
                            Log.d(
                                TAG, "paymentRequest error body ${paymentResponse.code()}" +
                                        " ${paymentResponse.message()}"
                            )
                        }
                    }
                    else {
                        _uiState.value = _uiState.value.copy(isLoading = false)
                        Log.d(
                            TAG, "getOrder error body ${orderResponse.code()}" +
                                    " ${orderResponse.message()}"
                        )
                    }
                }
                else {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    Log.d(
                        TAG,
                        "getToken error body ${tokenResponse.code()} ${tokenResponse.message()}"
                    )
                }

            }
            catch (e:Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
                Log.d(TAG, "getToken error: ${e.message}")
            }
        }
    }

    private fun confirmOrder(context: Context, order: Order, launchPayment: (Intent) -> Unit) {
        val payIntent = Intent(context, PayActivity::class.java)

        payIntent.putExtra("payment_key", _uiState.value.token)
        payIntent.putExtra("price", order.price)

        _uiState.value = _uiState.value.copy(isLoading = false)

        launchPayment(payIntent)
    }
}