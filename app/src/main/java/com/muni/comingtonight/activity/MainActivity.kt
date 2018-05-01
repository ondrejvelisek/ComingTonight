package com.muni.comingtonight.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.muni.comingtonight.R
import com.muni.comingtonight.api.GooglePlacesApi
import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.service.NearbyPlacesServiceStatic
import com.muni.comingtonight.service.ShowRatingServiceStatic
import com.muni.comingtonight.service.TvProgramServiceStatic
import com.muni.comingtonight.service.WeatherServiceStatic
import com.muni.comingtonight.strategy.BestOptionStrategyImpl
import io.nlopez.smartlocation.SmartLocation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_shows.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {


    private val tvProgramService = TvProgramServiceStatic()
    private val weatherService = WeatherServiceStatic()
    private val bestOptionStrategy = BestOptionStrategyImpl()
    private val nearbyPlacesService = NearbyPlacesServiceStatic()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SmartLocation.with(this).location()
                .start { location ->

                    val weather = weatherService.getEveningWeather(location)

                    temperatureText.text = weather.temperature.toString() + "°"
                    // TODO show more weather

                    val activities = mutableListOf<Activity>()
                    activities.addAll(tvProgramService.getTodaysMovies())
                    activities.addAll(nearbyPlacesService.getNearbyPlaces(location))

                    val activity = bestOptionStrategy.chooseBestActivity(weather, activities)

                    activityName.text = activity.name

                }

    }
}
