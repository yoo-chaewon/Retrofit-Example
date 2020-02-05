package com.example.weatherapp.Data

data class WeatherData(
    val common: Common,
    val result: Result,
    val weather: Weather<Any?>
)