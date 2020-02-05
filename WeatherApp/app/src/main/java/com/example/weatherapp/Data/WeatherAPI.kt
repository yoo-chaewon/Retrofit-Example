package com.example.weatherapp.Data

data class WeatherAPI(
    val common: Common,
    val result: Result,
    val weather: Weather
)