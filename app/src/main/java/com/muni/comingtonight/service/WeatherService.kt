package com.muni.comingtonight.service

import android.location.Location
import com.muni.comingtonight.model.Weather

interface WeatherService {

    fun getEveningWeather(location: Location) : Weather

}