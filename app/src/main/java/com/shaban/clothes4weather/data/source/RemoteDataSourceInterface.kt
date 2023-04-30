package com.shaban.clothes4weather.data.source

import com.shaban.clothes4weather.BuildConfig
import com.shaban.clothes4weather.data.models.WeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface RemoteDataSourceInterface {

    @GET("weather?lat=${29.3088779}&lon=${30.8259737}&appid=${BuildConfig.API_KEY}")
    fun getWeather(): Single<WeatherResponse>
}