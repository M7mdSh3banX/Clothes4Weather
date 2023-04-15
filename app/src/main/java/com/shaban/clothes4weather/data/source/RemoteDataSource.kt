package com.shaban.clothes4weather.data.source

import android.util.Log
import com.google.gson.Gson
import com.shaban.clothes4weather.data.models.WeatherResponse
import com.shaban.clothes4weather.utils.Constants.API_KEY_QUERY_PARAM
import com.shaban.clothes4weather.utils.Constants.API_KEY_VALUE
import com.shaban.clothes4weather.utils.Constants.HOST
import com.shaban.clothes4weather.utils.Constants.LATITUDE_QUERY_PARAM
import com.shaban.clothes4weather.utils.Constants.LONGITUDE_QUERY_PARAM
import com.shaban.clothes4weather.utils.Constants.PATH_SEGMENT_ONE
import com.shaban.clothes4weather.utils.Constants.PATH_SEGMENT_THREE
import com.shaban.clothes4weather.utils.Constants.PATH_SEGMENT_TWO
import com.shaban.clothes4weather.utils.Constants.SCHEME
import com.shaban.clothes4weather.utils.SharedPreferencesUtil
import okhttp3.*
import java.io.IOException

object RemoteDataSource {

    private val client = OkHttpClient()

    fun makeRequestUsingOKHTTP() {

        val url = HttpUrl
            .Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(PATH_SEGMENT_ONE)
            .addPathSegment(PATH_SEGMENT_TWO)
            .addPathSegment(PATH_SEGMENT_THREE)
            .addQueryParameter(LATITUDE_QUERY_PARAM, SharedPreferencesUtil.latitude.toString())
            .addQueryParameter(LONGITUDE_QUERY_PARAM, SharedPreferencesUtil.longitude.toString())
            .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY_VALUE)
            .build()

        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("TAG_TEST", "Fail Response: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.string()?.let {
                        val responseResult = Gson().fromJson(it, WeatherResponse::class.java)
                        Log.i("TAG_TEST", responseResult.toString())
                    }
                }
            }
        })
    }
}