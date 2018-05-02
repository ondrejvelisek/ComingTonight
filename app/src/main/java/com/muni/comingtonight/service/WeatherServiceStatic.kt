package com.muni.comingtonight.service

import android.location.Location
import com.muni.comingtonight.model.Weather

class WeatherServiceStatic : WeatherService {

    override suspend fun getEveningWeather(location: Location): Weather {
        return Weather("Újezd", 0.0, 18.5, 14.8)
    }

}