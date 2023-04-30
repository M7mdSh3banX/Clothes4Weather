package com.shaban.clothes4weather.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.shaban.clothes4weather.data.models.WeatherStatus

@BindingAdapter(value = ["temperatureInCelsius"])
fun setTemperatureInCelsius(textView: TextView, temperature: Float?) {
    textView.text = (temperature!! - 273.15)
        .toInt()
        .toString()
        .plus("°C")
}

@BindingAdapter(value = ["weatherStatus"])
fun setWeatherStatus(textView: TextView, weatherStatus: List<WeatherStatus>?) {
    textView.text = weatherStatus?.joinToString { it.statusDescription }
}