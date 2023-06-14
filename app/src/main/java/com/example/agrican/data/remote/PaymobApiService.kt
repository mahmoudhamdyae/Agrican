package com.example.agrican.data.remote

import com.example.agrican.data.remote.model.ApiKeyModel
import com.example.agrican.data.remote.model.GetTokenResponse
import com.example.agrican.data.remote.model.OrderModel
import com.example.agrican.data.remote.model.OrderResponse
import com.example.agrican.data.remote.model.PaymentRequest
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