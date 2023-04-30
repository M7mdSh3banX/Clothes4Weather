package com.shaban.clothes4weather.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.shaban.clothes4weather.R
import com.shaban.clothes4weather.data.models.WeatherStatus
import com.shaban.clothes4weather.data.source.LocalDataSource

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

@BindingAdapter(value = ["weatherStatusImage"])
fun setWeatherStatusImage(imageView: ImageView, weatherStatus: List<WeatherStatus>?) {
    imageView.apply {
        when (weatherStatus?.joinToString { it.iconWeatherStatus }) {
            "01d" -> setImageResource(R.drawable.sun)
            "02d" -> setImageResource(R.drawable.few_cloud)
            "03d", "03n" -> setImageResource(R.drawable.clouds)
            "04d" -> setImageResource(R.drawable.icon1)
            "09d" -> setImageResource(R.drawable.shower_rain)
            "10d", "09n" -> setImageResource(R.drawable.rainy)
            "11d", "11n" -> setImageResource(R.drawable.thunderstorm)
            "13d", "13n" -> setImageResource(R.drawable.snow)
            "50d", "04n", "50n" -> setImageResource(R.drawable.icon2)
            "01n" -> setImageResource(R.drawable.moon)
            "02n" -> setImageResource(R.drawable.scarred)
            "10n" -> setImageResource(R.drawable.rain)
            else -> setImageResource(R.drawable.sun)
        }
    }
}

@BindingAdapter(value = ["randomWeatherImage"])
fun setRandomWeatherImage(imageView: ImageView, temp: Float?) {
    val temperature = (temp!! - 273.15)
    val selectedImages = when {
        temperature <= 25.0F -> LocalDataSource.winterClothes
        temperature in 26.0F..60.0F -> LocalDataSource.summerClothes
        else -> LocalDataSource.winterClothes
    }
    val selectedImage = selectedImages.random()

    if (selectedImage == SharedPreferencesUtil.chosenOutfit)
        setRandomWeatherImage(imageView, temp)

    SharedPreferencesUtil.chosenOutfit = selectedImage
    imageView.setImageResource(selectedImage)
}

@BindingAdapter(value = ["weatherAdvice"])
fun setWeatherAdvice(textView: TextView, weatherStatus: List<WeatherStatus>?) {
    val index = when (weatherStatus?.joinToString { it.iconWeatherStatus }) {
        "01d", "01n" -> 0
        "02d", "02n", "03d", "03n", "04d", "04n", "50d", "50n" -> 4
        "09d", "09n", "10d", "10n" -> 2
        "11d", "11n" -> 3
        "13d", "13n" -> 1
        else -> 0
    }
    textView.apply {
        text = LocalDataSource.weatherAdvices[index]
    }
}