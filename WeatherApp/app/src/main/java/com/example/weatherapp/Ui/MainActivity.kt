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
import com.example.weatherapp.R
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

    private fun setData(weatherData: Objects){

    }
    private fun setBackground(weatherCode : String){

    }

    private fun setIcon(weatherCode: String){

    }

    private fun getWeather(latitude: String, longitude: String){

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

            if (mResultList != null) {
                Log.d("CheckCurrentLocation", mResultList[0].getAddressLine(0))
                currentLocation = mResultList[0].getAddressLine(0)
                currentLocation = currentLocation.substring(11)
            }

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
