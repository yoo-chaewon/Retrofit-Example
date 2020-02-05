package com.example.weatherapp.Data

data class Weather<T>(
    val minutely: List<Minutely>
)