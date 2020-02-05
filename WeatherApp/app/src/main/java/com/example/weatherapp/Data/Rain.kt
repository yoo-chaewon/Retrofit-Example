package com.example.weatherapp.Data

data class Rain(
    val last10min: String,
    val last12hour: String,
    val last15min: String,
    val last1hour: String,
    val last24hour: String,
    val last30min: String,
    val last6hour: String,
    val sinceMidnight: String,
    val sinceOntime: String
)