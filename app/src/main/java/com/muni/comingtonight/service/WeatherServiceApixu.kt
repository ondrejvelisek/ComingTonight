package com.muni.comingtonight.service

import android.location.Location
import com.muni.comingtonight.model.Weather

/**
 * Implementation based on Omdb API service http://www.apixu.com/
 * We registered following api key
 * apikey=5a09d63a43214eb79e585842181104
 */
class WeatherServiceApixu : WeatherService {

    override fun getEveningWeather(location: Location): Weather {
        TODO("not implemented")
    }

}