package com.shaban.clothes4weather.data.source

import com.shaban.clothes4weather.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val weatherService = retrofit.create(RemoteDataSourceInterface::class.java)
}