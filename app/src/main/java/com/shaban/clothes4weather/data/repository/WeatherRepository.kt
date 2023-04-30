package com.shaban.clothes4weather.data.repository

import com.shaban.clothes4weather.BuildConfig
import com.shaban.clothes4weather.data.source.RemoteDataSource
import com.shaban.clothes4weather.utils.SharedPreferencesUtil.latitude
import com.shaban.clothes4weather.utils.SharedPreferencesUtil.longitude

class WeatherRepository {

    fun getWeather() = RemoteDataSource.weatherService.getWeather(
        latitude,
        longitude,
        BuildConfig.API_KEY
    )
}