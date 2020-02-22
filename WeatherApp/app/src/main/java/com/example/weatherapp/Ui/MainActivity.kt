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
import android.widget.Toast
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
    private val REQUEST_CODE_LOCATION: Int = 2
    var currentLocation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnGetData.setOnClickListener {
            Log.d("click Refresh", "clicked")
            getCurrentLoc()
        }
        getCurrentLoc()
    }

    private fun setData(weatherData: WeatherData){
        tvLocation.text = weatherData.weather.minutely[0].station.name
        tvWeather.text = weatherData.weather.minutely[0].sky.name
        tvCurTemp.text = "${weatherData.weather.minutely[0].temperature.tc}°"

        var tmax: Double  = weatherData.weather.minutely[0].temperature.tmax.toDouble()
        var tmin: Double  = weatherData.weather.minutely[0].temperature.tmin.toDouble()

        tvMaxMinTemp.text = "최고 ${tmax.toInt()}° 최소 ${tmin.toInt()}°"
        setBackground(weatherData.weather.minutely[0].sky.code)
        setIcon(weatherData.weather.minutely[0].sky.code)
    }
    private fun setBackground(weatherCode : String){
        when(weatherCode){
            "SKY_A01" -> mainView.setBackgroundResource(R.drawable.sky_a01)
            "SKY_A02" -> mainView.setBackgroundResource(R.drawable.sky_a02)
            "SKY_A03" -> mainView.setBackgroundResource(R.drawable.sky_a03)
            "SKY_A04" -> mainView.setBackgroundResource(R.drawable.sky_a04)
            "SKY_A05" -> mainView.setBackgroundResource(R.drawable.sky_a05)
            "SKY_A06" -> mainView.setBackgroundResource(R.drawable.sky_a06)
            "SKY_A07" -> mainView.setBackgroundResource(R.drawable.sky_a07)
            "SKY_A08" -> mainView.setBackgroundResource(R.drawable.sky_a08)
            "SKY_A09" -> mainView.setBackgroundResource(R.drawable.sky_a09)
            "SKY_A10" -> mainView.setBackgroundResource(R.drawable.sky_a10)
            "SKY_A11" -> mainView.setBackgroundResource(R.drawable.sky_a11)
            "SKY_A12" -> mainView.setBackgroundResource(R.drawable.sky_a12)
            "SKY_A13" -> mainView.setBackgroundResource(R.drawable.sky_a13)
            "SKY_A14" -> mainView.setBackgroundResource(R.drawable.sky_a14)
            else -> mainView.setBackgroundResource(R.drawable.sky_a14)
        }
    }

    private fun setIcon(weatherCode: String){
        when(weatherCode){
            "SKY_A01" -> iconWeather.setBackgroundResource(R.drawable.ic_sun)
            "SKY_A02" -> iconWeather.setBackgroundResource(R.drawable.ic_cloudy)
            "SKY_A03" -> iconWeather.setBackgroundResource(R.drawable.ic_clouds)
            "SKY_A04" -> iconWeather.setBackgroundResource(R.drawable.ic_rain_1)
            "SKY_A05" -> iconWeather.setBackgroundResource(R.drawable.ic_hail)
            "SKY_A06" -> iconWeather.setBackgroundResource(R.drawable.ic_hail)
            "SKY_A07" -> iconWeather.setBackgroundResource(R.drawable.ic_clouds)
            "SKY_A08" -> iconWeather.setBackgroundResource(R.drawable.ic_rain_1)
            "SKY_A09" -> iconWeather.setBackgroundResource(R.drawable.ic_hail)
            "SKY_A10" -> iconWeather.setBackgroundResource(R.drawable.ic_hail)
            "SKY_A11" -> iconWeather.setBackgroundResource(R.drawable.ic_storm)
            "SKY_A12" -> iconWeather.setBackgroundResource(R.drawable.ic_storm)
            "SKY_A13" -> iconWeather.setBackgroundResource(R.drawable.ic_storm)
            "SKY_A14" -> iconWeather.setBackgroundResource(R.drawable.ic_storm)
            else -> iconWeather.setBackgroundResource(R.drawable.ic_sun)
        }
    }

    private fun getWeather(latitude: String, longitude: String){
        NetworkCore.getNetworkCore<WeatherAPI>()
           .getCurrentWeatherData(getString(R.string.appKey), latitude, longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                setData(it)
            },{
                it.printStackTrace()
            })
    }

    private fun getCurrentLoc() {
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var userLocation: Location = getLatLng(locationManager)
        if (userLocation != null) {
            var latitude: Double = userLocation.latitude
            var longitude: Double = userLocation.longitude
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

//            if (mResultList != null) {
//                Log.d("CheckCurrentLocation", mResultList[0].getAddressLine(0))
//                currentLocation = mResultList[0].getAddressLine(0)
//                currentLocation = currentLocation.substring(11)
//            }
            getWeather(latitude.toString(), longitude.toString())
        }
    }

    private fun getLatLng(locationManager: LocationManager): Location {
        var currentLatLng: Location? = null
        if (ActivityCompat.checkSelfPermission(applicationContext, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(applicationContext, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), this.REQUEST_CODE_LOCATION)
            getLatLng(locationManager)
        }else{
            val locationProvider = LocationManager.GPS_PROVIDER
            currentLatLng = locationManager?.getLastKnownLocation(locationProvider)

        }
        return currentLatLng!!
    }
}
