package com.shaban.clothes4weather.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["temperatureInCelsius"])
fun setTemperatureInCelsius(textView: TextView, temperature: Float?) {
    textView.text = (temperature!! - 273.15)
        .toInt()
        .toString()
        .plus("°C")
}