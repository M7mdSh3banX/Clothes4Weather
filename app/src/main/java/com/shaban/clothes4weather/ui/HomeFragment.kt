package com.shaban.clothes4weather.ui

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.shaban.clothes4weather.R
import com.shaban.clothes4weather.data.models.WeatherResponse
import com.shaban.clothes4weather.data.source.LocalDataSource
import com.shaban.clothes4weather.data.source.RemoteDataSource
import com.shaban.clothes4weather.data.source.RemoteDataSourceInterface
import com.shaban.clothes4weather.databinding.FragmentHomeBinding
import com.shaban.clothes4weather.ui.base.BaseFragment
import com.shaban.clothes4weather.utils.SharedPreferencesUtil
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class HomeFragment : BaseFragment<FragmentHomeBinding>(), RemoteDataSourceInterface {
    override val TAG: String
        get() = this::class.simpleName.toString()

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)


    override fun setup() {
        SharedPreferencesUtil.initPreferencesUtil(requireContext())

        RemoteDataSource.makeRequestUsingOKHTTP(this)
        isNightMode()
    }

    private fun isNightMode() {
        if (SharedPreferencesUtil.isNightMode!!) {
            binding.switchMode.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        binding.switchMode.setOnClickListener {
            if (SharedPreferencesUtil.isNightMode!!) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                SharedPreferencesUtil.isNightMode = false
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                SharedPreferencesUtil.isNightMode = true
            }
        }
    }

    override fun onSuccess(weatherResponse: WeatherResponse) {
        activity?.runOnUiThread {
            binding.cityNameTextView.text = weatherResponse.cityName
            binding.weatherStatusTextView.text = weatherResponse.weatherStatus.joinToString {
                it.statusDescription
            }
            binding.tempratureTextView.text =
                (weatherResponse.weatherMainDetails.temperature - 273.15)
                    .toInt()
                    .toString()
                    .plus("°C")
            setWeatherStatusImage(weatherResponse)
            binding.dateTextView.text = getCurrentDate()
            binding.selectedImageView.setImageResource(getRandomWeatherImage(weatherResponse))
            binding.switchIcon.setOnClickListener {
                val randomWeatherImage = getRandomWeatherImage(weatherResponse)
                binding.selectedImageView.setImageResource(randomWeatherImage)
            }
        }
    }

    override fun onError(messageError: String) {
        Log.i(TAG, "Fail Response: $messageError")
    }

    private fun getCurrentDate(): String {
        val formatter = DateTimeFormatter.ofPattern("EEE, MMM dd")
        val current = LocalDateTime.now().format(formatter)
        Log.e("LOCAL_TIME", current)
        return current
    }

    private fun getRandomWeatherImage(weatherResponse: WeatherResponse): Int {
        val temperature = weatherResponse.weatherMainDetails.temperature - 273.15
        val selectedImages = when {
            temperature <= 25.0F -> LocalDataSource.winterClothes
            temperature in 26.0F..60.0F -> LocalDataSource.summerClothes
            else -> LocalDataSource.winterClothes
        }
        val selectedImage = selectedImages.random()

        if (selectedImage == SharedPreferencesUtil.chosenOutfit)
            getRandomWeatherImage(weatherResponse)

        SharedPreferencesUtil.chosenOutfit = selectedImage
        return selectedImage
    }

    private fun setWeatherStatusImage(weatherResponse: WeatherResponse) {
        binding.weatherStatusIcon.apply {
            when (weatherResponse.weatherStatus.joinToString { it.iconWeatherStatus }) {
                "01d" -> setImageResource(R.drawable.sun)
                "02d" -> setImageResource(R.drawable.few_cloud)
                "03d" -> setImageResource(R.drawable.clouds)
                "04d" -> setImageResource(R.drawable.icon1)
                "09d" -> setImageResource(R.drawable.shower_rain)
                "10d" -> setImageResource(R.drawable.rainy)
                "11d" -> setImageResource(R.drawable.thunderstorm)
                "13d" -> setImageResource(R.drawable.snow)
                "50d" -> setImageResource(R.drawable.icon2)
                "01n" -> setImageResource(R.drawable.moon)
                "02n" -> setImageResource(R.drawable.scarred)
                "03n" -> setImageResource(R.drawable.clouds)
                "04n" -> setImageResource(R.drawable.icon2)
                "09n" -> setImageResource(R.drawable.rainy)
                "10n" -> setImageResource(R.drawable.rain)
                "11n" -> setImageResource(R.drawable.thunderstorm)
                "13n" -> setImageResource(R.drawable.snow)
                "50n" -> setImageResource(R.drawable.icon2)
                else -> setImageResource(R.drawable.sun)
            }
        }
    }
}