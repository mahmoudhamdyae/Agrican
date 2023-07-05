package com.example.agrican.data.remote.model.paymob

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderModel(
    @SerializedName("auth_token")
    @Expose
    val authToken: String,
    @SerializedName("delivery_needed")
    @Expose
    val deliveryNeeded: String,
    @SerializedName("amount_cents")
    @Expose
    val amountCents: String,
    @SerializedName("currency")
    @Expose
    val currency: String
)