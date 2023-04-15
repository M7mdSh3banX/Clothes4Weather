package com.shaban.clothes4weather.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("name") val cityName: String,
    @SerializedName("weather") val weatherStatus: List<WeatherStatus>,
    @SerializedName("main") val weatherMainDetails: WeatherMainDetails,
)