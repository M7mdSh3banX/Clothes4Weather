package com.shaban.clothes4weather.ui

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.shaban.clothes4weather.data.models.WeatherResponse
import com.shaban.clothes4weather.data.source.RemoteDataSource
import com.shaban.clothes4weather.data.source.RemoteDataSourceInterface
import com.shaban.clothes4weather.databinding.FragmentHomeBinding
import com.shaban.clothes4weather.ui.base.BaseFragment
import com.shaban.clothes4weather.utils.SharedPreferencesUtil
import java.time.LocalDate
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
            binding.dateTextView.text = getCurrentDate()
        }

    }

    override fun onError(messageError: String) {
        Log.i("TAG_TEST", "Fail Response: $messageError")
    }

    private fun getCurrentDate(): String {
        val formatter = DateTimeFormatter.ofPattern("EEE, MMM dd")
        val current = LocalDateTime.now().format(formatter)
        Log.e("LOCAL_TIME", current)
        return current
    }
}