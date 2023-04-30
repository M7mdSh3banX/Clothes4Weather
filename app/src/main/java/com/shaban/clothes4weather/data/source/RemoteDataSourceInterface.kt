package com.shaban.clothes4weather.data.source

import com.shaban.clothes4weather.data.models.WeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSourceInterface {

    @GET("weather")
    fun getWeather(
        @Query("lat") latitude: Float?,
        @Query("lon") longitude: Float?,
        @Query("appid") apiKey: String
    ): Single<WeatherResponse>

}