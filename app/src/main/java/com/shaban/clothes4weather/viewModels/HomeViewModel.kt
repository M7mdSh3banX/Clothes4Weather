package com.shaban.clothes4weather.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaban.clothes4weather.data.repository.WeatherRepository
import com.shaban.clothes4weather.data.models.WeatherResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val repository = WeatherRepository()

    private val _weatherResponseMutableLiveData = MutableLiveData<WeatherResponse>()
    val weatherResponseLiveData: LiveData<WeatherResponse>
        get() = _weatherResponseMutableLiveData

    init {
        getWeatherResponse()
    }

    fun getWeatherResponse() {
        compositeDisposable.add(
            repository.getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError)
        )
    }

    private fun onSuccess(weatherResponse: WeatherResponse) =
        _weatherResponseMutableLiveData.postValue(weatherResponse)

    private fun onError(errorMessage: Throwable) =
        Log.e(LOG_TAG, errorMessage.message.toString())


    companion object {
        private val LOG_TAG = "HomeViewModel"
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}