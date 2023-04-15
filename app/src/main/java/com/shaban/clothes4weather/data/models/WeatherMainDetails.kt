package com.shaban.clothes4weather.data.models

import com.google.gson.annotations.SerializedName

data class WeatherMainDetails(
    @SerializedName("temp") val temperature: Float
)