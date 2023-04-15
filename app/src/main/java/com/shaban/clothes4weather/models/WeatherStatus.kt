package com.shaban.clothes4weather.models

import com.google.gson.annotations.SerializedName

data class WeatherStatus(
    @SerializedName("main") val mainStatus: String,
    @SerializedName("description") val statusDescription: String
)