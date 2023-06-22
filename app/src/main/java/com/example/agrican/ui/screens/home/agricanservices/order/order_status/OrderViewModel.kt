package com.example.agrican.ui.screens.home.agricanservices.order.order_status

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.agrican.R
import com.example.agrican.common.utils.Constant
import com.example.agrican.data.remote.PaymobApiService
import com.example.agrican.data.remote.model.ApiKeyModel
import com.example.agrican.data.remote.model.BillingData
import com.example.agrican.data.remote.model.OrderModel
import com.example.agrican.data.remote.model.PaymentRequest
import com.example.agrican.domain.model.Order
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import com.paymob.acceptsdk.PayActivity
import com.paymob.acceptsdk.PayActivityIntentKeys
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
                            confirmOrder(context, order, launchPayment)
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

    private fun confirmOrder(context: Context, order: Order, launchPayment: (Intent) -> Unit) {
        val payIntent = Intent(context, PayActivity::class.java)

        payIntent.putExtra(PayActivityIntentKeys.PAYMENT_KEY, _uiState.value.token)
        payIntent.putExtra(PayActivityIntentKeys.THREE_D_SECURE_ACTIVITY_TITLE, "Verification")

        payIntent.putExtra("language", "en")
        payIntent.putExtra(PayActivityIntentKeys.TOKEN, "6088c38c19705a495f1727561d4f4814b2ed7e45e9cd80c72f233253")
        payIntent.putExtra(PayActivityIntentKeys.MASKED_PAN_NUMBER, "5123-4567-8901-2346")
        payIntent.putExtra(PayActivityIntentKeys.SAVE_CARD_DEFAULT, false)
        payIntent.putExtra(PayActivityIntentKeys.SHOW_SAVE_CARD, false)
        payIntent.putExtra("ActionBar", true)
        payIntent.putExtra(
            PayActivityIntentKeys.THEME_COLOR,
            context.getColor(R.color.purple_500)
        )

        payIntent.putExtra(PayActivityIntentKeys.FIRST_NAME, _uiState.value.currentUser.userName)
        payIntent.putExtra(PayActivityIntentKeys.LAST_NAME, _uiState.value.currentUser.userName)
        payIntent.putExtra(PayActivityIntentKeys.BUILDING, "NA")
        payIntent.putExtra(PayActivityIntentKeys.FLOOR, "NA")
        payIntent.putExtra(PayActivityIntentKeys.APARTMENT, "NA")
        payIntent.putExtra(PayActivityIntentKeys.CITY, "NA")
        payIntent.putExtra(PayActivityIntentKeys.STATE, "NA")
        payIntent.putExtra(PayActivityIntentKeys.COUNTRY, "NA")
        payIntent.putExtra(PayActivityIntentKeys.EMAIL, _uiState.value.currentUser.email)
        payIntent.putExtra(PayActivityIntentKeys.PHONE_NUMBER, _uiState.value.currentUser.phoneNumber)
        payIntent.putExtra(PayActivityIntentKeys.POSTAL_CODE, "NA")

        launchPayment(payIntent)
    }
}