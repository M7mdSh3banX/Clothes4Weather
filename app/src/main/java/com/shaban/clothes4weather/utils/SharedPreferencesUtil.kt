package com.shaban.clothes4weather.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtil {
    private var sharedPreferences: SharedPreferences? = null
    private const val SHARED_PREFERENCES_NAME = "Clothes4WeatherSharedPreferences"
    private const val LATITUDE_KEY = "latitudeKey"
    private const val LONGITUDE_KEY = "longitudeKey"
    private const val NIGHT_MODE_KEY = "nightModeKey"
    private const val CHOSEN_OUTFIT_KEY = "chosenOutfitKey"

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

    var isNightMode: Boolean?
        get() = sharedPreferences?.getBoolean(NIGHT_MODE_KEY, false)
        set(value) {
            sharedPreferences?.edit()?.putBoolean(NIGHT_MODE_KEY, value!!)?.apply()
        }

    var chosenOutfit: Int?
        get() = sharedPreferences?.getInt(CHOSEN_OUTFIT_KEY, 0)
        set(value) {
            sharedPreferences?.edit()?.putInt(CHOSEN_OUTFIT_KEY, value!!)?.apply()
        }
}