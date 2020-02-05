package com.example.weatherapp.Data

data class Minutely(
    val humidity: String,
    val lightning: String,
    val precipitation: Precipitation,
    val pressure: Pressure,
    val rain: Rain,
    val sky: Sky,
    val station: Station,
    val temperature: Temperature,
    val timeObservation: String,
    val wind: Wind
)