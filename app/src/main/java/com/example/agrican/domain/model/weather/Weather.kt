package com.example.agrican.domain.model.weather

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val degree: Double,
    val wind: Double,
    val windGusts: Double,
    @StringRes val windDirection: Int,
    @StringRes val weatherDesc: Int,
    @StringRes val airQuality: Int,
    @DrawableRes val iconRes: Int,
): Parcelable

class AssetParamType: NavType<Weather>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Weather? {
        @Suppress("DEPRECATION")
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Weather {
        return Gson().fromJson(value, Weather::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Weather) {
        bundle.putParcelable(key, value)
    }
}

fun Weather.toJson(): String {
    return Uri.encode(Gson().toJson(this))
}