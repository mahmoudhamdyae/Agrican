package com.example.agrican.ui.screens.home.agricanservices.order.order_status

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.graphics.toArgb
import com.example.agrican.common.utils.Constant
import com.example.agrican.data.remote.PaymobApiService
import com.example.agrican.data.remote.model.ApiKeyModel
import com.example.agrican.data.remote.model.BillingData
import com.example.agrican.data.remote.model.OrderModel
import com.example.agrican.data.remote.model.PaymentRequest
import com.example.agrican.domain.model.Order
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.PayActivity
import com.example.agrican.ui.theme.greenDark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val TAG = "OrderViewModel"

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    private val apiService: PaymobApiService,
): BaseViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launchCatching {
            _uiState.value = _uiState.value.copy(
                orders = useCase.getOrdersUseCase(),
                currentUser = useCase.getCurrentUserUseCase()
            )
        }
    }

    fun getTokenAndConfirmOrder(context: Context, order: Order, launchPayment: (Intent) -> Unit) {
        launchCatching {
            try {
                val tokenResponse = apiService.getToken(ApiKeyModel(Constant.PAYMENT_KEY))

                if (tokenResponse.isSuccessful) {
                    Log.d(TAG, "getToken done ${tokenResponse.body()?.token}")
                    val token = tokenResponse.body()?.token

                    val orderResponse =  apiService.getOrder(
                        OrderModel(token.toString(), "false", order.price.toString(), order.currency)
//                        OrderModel(token.toString(), "false", order.price.toString(), "EGP")
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
                            confirmOrder(context, launchPayment)
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

    private fun confirmOrder(context: Context, launchPayment: (Intent) -> Unit) {
        val payIntent = Intent(context, PayActivity::class.java)

        payIntent.putExtra("payment_key", _uiState.value.token)
        payIntent.putExtra("three_d_secure_activity_title", "Verification")
        payIntent.putExtra("save_card_default", false)
        payIntent.putExtra("show_save_card", false)
        payIntent.putExtra("theme_color", greenDark.toArgb())
        payIntent.putExtra("ActionBar", false)
        payIntent.putExtra("language", AppCompatDelegate.getApplicationLocales().toLanguageTags())

        launchPayment(payIntent)
    }
}