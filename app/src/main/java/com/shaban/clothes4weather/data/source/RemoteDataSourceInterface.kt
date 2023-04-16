package com.shaban.clothes4weather.data.source

import com.shaban.clothes4weather.data.models.WeatherResponse

interface RemoteDataSourceInterface {
    fun onSuccess(weatherResponse: WeatherResponse)
    fun onError(messageError: String)
}