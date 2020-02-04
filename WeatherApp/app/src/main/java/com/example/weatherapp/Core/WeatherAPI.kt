package com.example.weatherapp.Core

import com.example.weatherapp.Data.Weather
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*


interface WeatherAPI {
    //TODO api 정리

//    //Any == object
//    @POST("/meal")
//    fun sendMeal(@Body body: HashMap<String,Any>) : Completable
//
//    @POST("/workout")
//    fun sendStep(
//        @Header("Authorization") authorization: String,
//        @Body body: StepData
//    ) : Completable
//
//
//
//    @GET("/exercises") // 리턴타입
//    fun getExcercises(
//        @Header("Authorization") authorization: String
//    ) : Single<List<Exercise>>
//
//    @GET("/main")
//    fun getMain(
//        @Header("Authorization") authorization: String
//    ) : Single<MainResponse>


    //http://api.openweathermap.org/data/2.5/weather?lat=37&lon=126&APPID=92d535574f86e3834d553e34e15e9dba&units=metric
    @GET("/data/2.5/weather?/units=metric&")
    fun getCurrentWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("APPID") app_id: String
    )


}