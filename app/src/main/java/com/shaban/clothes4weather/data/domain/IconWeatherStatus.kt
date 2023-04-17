package com.shaban.clothes4weather.data.domain

import com.shaban.clothes4weather.utils.Constants.ICON_BASE_URL

object IconWeatherStatus {

    fun iconCode(code: String): String {
        return "${ICON_BASE_URL}$code@4x.png"
    }
}
