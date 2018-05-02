package com.muni.comingtonight.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.muni.comingtonight.R
import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.service.NearbyPlacesServiceStatic
import com.muni.comingtonight.service.TvProgramServiceStatic
import com.muni.comingtonight.service.WeatherServiceApixu
import kotlinx.coroutines.experimental.android.UI
import com.muni.comingtonight.strategy.BestOptionStrategyImpl
import io.nlopez.smartlocation.SmartLocation
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View.VISIBLE
import com.muni.comingtonight.service.NearbyPlacesServiceGoogle
import com.squareup.picasso.Picasso
import kotlinx.coroutines.experimental.*


class MainActivity : AppCompatActivity() {

    private val tvProgramService = TvProgramServiceStatic()
    private val weatherService = WeatherServiceApixu()
    private val nearbyPlacesService = NearbyPlacesServiceGoogle()
    private val bestOptionStrategy = BestOptionStrategyImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SmartLocation.with(this).location()
                .start { location ->

                    launch(UI) {

                        val weather = withContext(CommonPool) { weatherService.getEveningWeather(location) }

                        locationNameText.text = weather.locationName
                        temperatureText.text = "%.1f°".format(weather.temperature)
                        if (weather.precipitation > 0.1) {
                            weatherIcon1.setImageResource(R.drawable.ic_rainy)
                            weatherIcon1.visibility = VISIBLE
                        } else {
                            weatherIcon1.setImageResource(R.drawable.ic_sunny)
                            weatherIcon1.visibility = VISIBLE
                        }
                        if (weather.wind > 30) {
                            weatherIcon2.setImageResource(R.drawable.ic_windy)
                            weatherIcon2.visibility = VISIBLE
                        }

                        val moviesDfr = async(CommonPool) { tvProgramService.getTodaysMovies() }
                        val placesDfr = async(CommonPool) { nearbyPlacesService.getNearbyPlaces(location) }

                        val activities = mutableListOf<Activity>()
                        activities.addAll(moviesDfr.await())
                        activities.addAll(placesDfr.await())

                        val activity = bestOptionStrategy.chooseBestActivity(weather, activities)

                        activityName.text = activity.name

                        if (activity.imageUri != null) {
                            Picasso.with(baseContext)
                                    .load(activity.imageUri?.toString())
                                    .into(posterImage)
                        }

                    }

                }

    }
}
