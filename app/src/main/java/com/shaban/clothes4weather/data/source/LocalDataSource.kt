package com.shaban.clothes4weather.data.source

import com.shaban.clothes4weather.R

object LocalDataSource {
    val summerClothes = listOf(
        (R.drawable.summer_outfit1),
        (R.drawable.summer_outfit2),
        (R.drawable.summer_outfit3),
        (R.drawable.summer_outfit4),
        (R.drawable.summer_outfit5),
        (R.drawable.summer_outfit6),
        (R.drawable.summer_outfit7),
        (R.drawable.summer_outfit8),
        (R.drawable.summer_outfit9),
        (R.drawable.summer_outfit1)
    )

    val winterClothes = listOf(
        R.drawable.winter_outfit1,
        R.drawable.winter_outfit2,
        R.drawable.winter_outfit3,
        R.drawable.winter_outfit4,
        R.drawable.winter_outfit5,
        R.drawable.winter_outfit6,
        R.drawable.winter_outfit7,
        R.drawable.winter_outfit8,
        R.drawable.winter_outfit9,
        R.drawable.winter_outfit1
    )

    private const val sunnyWeatherAdvice =
        "Stay hydrated: drink plenty of cool liquids, especially water, before you feel thirsty to decrease your risk of dehydration. Avoid sun exposure: wear a wide-brimmed, breathable hat or use an umbrella. Wear sunglasses: make sure they provide protection against UVA and UVB rays."
    private const val winterWeatherAdvice =
        "Wet clothing can make you feel colder, so try to stay dry by wearing a waterproof outer layer and avoiding activities that will make you sweat. If for any reason your clothes get wet due to rain or otherwise, change into dry ones as soon as you can."
    private const val rainnyWeatherAdvice =
        "Walking in the rain may appear fun, but unless you are wearing boots and a rain jacket, it is best to just wait it out and stay dry. Getting wet in the rain exposes you to various viral diseases, like leptospirosis, and can also result in a variety of fungal infections of the feet and nails. Do not forget to bring alcohol, hand sanitizer, and some wipes to keep yourself clean before leaving your home."
    private const val thundrstormWeatherAdvice =
        "When you hear thunder, seek shelter inside a sturdy building and move to a basement or an interior room on the lowest floor. Stay away from glass windows and doors. Stay inside until weather forecasts indicate it is safe to leave. Lightning can be dangerous even when you are inside."
    private const val cloudsWeatherAdvice =
        "Here are some hints for predicting weather by reading clouds. Isolated, wispy, or very high clouds are an indication of fair weather. Crowded, dense, dark, and towering clouds indicate changing or worsening weather. The sharper the edge of a thundercloud and the darker its color, the more violence it may contain."

    val weatherAdvices = listOf(
        sunnyWeatherAdvice,
        winterWeatherAdvice,
        rainnyWeatherAdvice,
        thundrstormWeatherAdvice,
        cloudsWeatherAdvice
    )
}