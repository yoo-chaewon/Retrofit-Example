package com.example.weatherapp.Ui

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.weatherapp.Core.NetworkCore
import com.example.weatherapp.Core.WeatherAPI
import com.example.weatherapp.Data.WeatherData
import com.example.weatherapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    var locationManager: LocationManager? = null
    private val REQUEST_CODE_LOCATION: Int = 2
    var currentLocation: String = ""
    private var latitude: Double? = null
    private var longitude: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnGetData.setOnClickListener {  }
        getCurrentLoc()
        getWeather()


    }

    private fun setData(weatherData: WeatherData){
        tvLocation.text = weatherData.weather.minutely[0].station.name
        tvWeather.text = weatherData.weather.minutely[0].sky.name
        tvCurTemp.text = weatherData.weather.minutely[0].temperature.tc
        //etBackground(weatherData.)

    }
    private fun setBackground(weatherCode : String){

    }

    private fun getWeather(){
        NetworkCore.getNetworkCore<WeatherAPI>()
           .getCurrentWeatherData(getString(R.string.appKey), latitude.toString(), longitude.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                setData(it)
            },{
                it.printStackTrace()
            })
    }

    private fun getCurrentLoc() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        var userLocation: Location = getLatLng()
        if (userLocation != null) {
            latitude = userLocation.latitude
            longitude = userLocation.longitude
            Log.d("CheckCurrentLocation", "현재 내 위치: $latitude, $longitude")

            var mGeocoder = Geocoder(applicationContext, Locale.KOREAN)
            var mResultList: List<Address>? = null
            try {
                mResultList = mGeocoder.getFromLocation(
                    latitude!!, longitude!!, 1
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (mResultList != null) {
                Log.d("CheckCurrentLocation", mResultList[0].getAddressLine(0))
                currentLocation = mResultList[0].getAddressLine(0)
                currentLocation = currentLocation.substring(11)
            }
        }
    }

    private fun getLatLng(): Location {
        var currentLatLng: Location? = null
        if (ActivityCompat.checkSelfPermission(applicationContext, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(applicationContext, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), this.REQUEST_CODE_LOCATION)
            getLatLng()
        }else{
            val locationProvider = LocationManager.GPS_PROVIDER
            currentLatLng = locationManager?.getLastKnownLocation(locationProvider)

        }
        return currentLatLng!!
    }
}
