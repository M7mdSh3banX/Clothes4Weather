package com.shaban.clothes4weather.data

import com.shaban.clothes4weather.data.source.RemoteDataSource

class WeatherRepository {

    fun getWeather() = RemoteDataSource.weatherService.getWeather()
}