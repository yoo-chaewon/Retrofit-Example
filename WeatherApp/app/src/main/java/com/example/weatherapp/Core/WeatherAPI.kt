package com.example.weatherapp.Core

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


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
}