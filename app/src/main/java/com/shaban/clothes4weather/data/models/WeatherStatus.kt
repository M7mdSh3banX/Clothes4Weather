package com.shaban.clothes4weather.data.models

import com.google.gson.annotations.SerializedName

data class WeatherStatus(
    @SerializedName("main") val mainStatus: String,
    @SerializedName("description") val statusDescription: String,
    @SerializedName("icon") val iconWeatherStatus: String
)