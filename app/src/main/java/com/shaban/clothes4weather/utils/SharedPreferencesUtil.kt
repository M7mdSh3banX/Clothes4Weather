package com.shaban.clothes4weather.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtil {
    private var sharedPreferences: SharedPreferences? = null
    private const val SHARED_PREFERENCES_NAME = "Clothes4WeatherSharedPreferences"
    private const val LATITUDE_KEY = "latitudeKey"
    private const val LONGITUDE_KEY = "longitudeKey"

    fun initPreferencesUtil(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
    }

    var latitude: Float?
        get() = sharedPreferences?.getFloat(LATITUDE_KEY, 0.0F)
        set(value) {
            sharedPreferences?.edit()?.putFloat(LATITUDE_KEY, value!!)?.apply()
        }

    var longitude: Float?
        get() = sharedPreferences?.getFloat(LONGITUDE_KEY, 0.0F)
        set(value) {
            sharedPreferences?.edit()?.putFloat(LONGITUDE_KEY, value!!)?.apply()
        }
}