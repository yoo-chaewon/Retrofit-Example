package com.example.weatherapp.Core

import com.example.weatherapp.Data.WeatherData
import io.reactivex.Single
import retrofit2.http.*


interface WeatherAPI {
    @GET("/weather/current/minutely?")
    fun getCurrentWeatherData(
        @Query("appKey") app_id: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ) : Single<WeatherData>
}