package com.muni.comingtonight.service

import android.location.Location
import com.muni.comingtonight.model.Weather

class WeatherServiceStatic : WeatherService {

    override fun getEveningWeather(location: Location): Weather {
        return Weather(0.0, 18.5, 14.8)
    }

}