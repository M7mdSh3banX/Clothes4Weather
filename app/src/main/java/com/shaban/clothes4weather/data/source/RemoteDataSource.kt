package com.shaban.clothes4weather.data.source

import android.util.Log
import com.google.gson.Gson
import com.shaban.clothes4weather.BuildConfig
import com.shaban.clothes4weather.data.models.WeatherResponse
import com.shaban.clothes4weather.utils.Constants
import com.shaban.clothes4weather.utils.SharedPreferencesUtil.latitude
import com.shaban.clothes4weather.utils.SharedPreferencesUtil.longitude
import okhttp3.*
import java.io.IOException

object RemoteDataSource {

    private val client = OkHttpClient()
    fun makeRequestUsingOKHTTP(callBack: RemoteDataSourceInterface) {

        val request = Request.Builder()
            .url("${Constants.BASE_URL}/weather?lat=$latitude&lon=$longitude&appid=${BuildConfig.API_KEY}")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callBack.onError(e.message!!)
                Log.i("TAG_TEST", "Fail Response: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.string()?.let {
                        val responseResult = Gson().fromJson(it, WeatherResponse::class.java)
                        callBack.onSuccess(responseResult)
                        Log.i("TAG_TEST", responseResult.toString())
                    }
                }
            }
        })
    }
}