package com.theflankers.agrican.data.remote

import com.theflankers.agrican.data.remote.model.paymob.ApiKeyModel
import com.theflankers.agrican.data.remote.model.paymob.GetTokenResponse
import com.theflankers.agrican.data.remote.model.paymob.OrderModel
import com.theflankers.agrican.data.remote.model.paymob.OrderResponse
import com.theflankers.agrican.data.remote.model.paymob.PaymentRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymobApiService {

    @POST("auth/tokens")
    suspend fun getToken(@Body api_key: ApiKeyModel): Response<GetTokenResponse>

    @POST("ecommerce/orders")
    suspend fun getOrder(@Body orderModel: OrderModel): Response<OrderResponse>

    @POST("acceptance/payment_keys")
    suspend fun paymentRequest(@Body paymentRequest: PaymentRequest): Response<GetTokenResponse>
}