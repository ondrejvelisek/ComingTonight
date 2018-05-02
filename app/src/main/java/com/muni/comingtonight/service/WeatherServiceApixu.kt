package com.muni.comingtonight.service

import android.location.Location
import com.muni.comingtonight.api.ApixuApi
import com.muni.comingtonight.model.Weather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.retrofit.await

/**
 * Implementation based on Apixu API service https://www.apixu.com/doc/request.aspx
 * We registered following api key
 * apikey=5a09d63a43214eb79e585842181104
 */
class WeatherServiceApixu : WeatherService {

    val apixuApi = Retrofit.Builder()
            .baseUrl("https://api.apixu.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApixuApi::class.java)

    override suspend fun getEveningWeather(location: Location): Weather {

        val forecast = apixuApi.todaysForecast(
                "%f,%f".format(location.latitude, location.longitude),
                "5a09d63a43214eb79e585842181104"
        ).await()

        val locationName = forecast
                .getAsJsonObject("location")
                .getAsJsonPrimitive("name")
                .asString

        val day = forecast
                .getAsJsonObject("forecast")
                .getAsJsonArray("forecastday")
                .get(0).asJsonObject
                .getAsJsonObject("day")

        val temp = day.getAsJsonPrimitive("avgtemp_c").asDouble
        val prec = day.getAsJsonPrimitive("totalprecip_mm").asDouble
        val wind = day.getAsJsonPrimitive("maxwind_kph").asDouble

        return Weather(locationName, prec, temp, wind)
    }

}