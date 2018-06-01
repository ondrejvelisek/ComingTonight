package com.muni.comingtonight.activity

import android.content.Intent
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.muni.comingtonight.R.*
import com.muni.comingtonight.model.Activity
import kotlinx.coroutines.experimental.android.UI
import com.muni.comingtonight.strategy.TresholdChooseActivityStrategy
import io.nlopez.smartlocation.SmartLocation
import com.muni.comingtonight.service.*
import kotlinx.android.synthetic.main.activity_loading.*
import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.suspendCoroutine

const val EXTRA_BEST_ACTIVITY = "EXTRA_BEST_ACTIVITY"
const val EXTRA_ACTIVITIES = "EXTRA_ACTIVITIES"

class LoadingActivity : AppCompatActivity() {

    private val tvProgramService = TvProgramServiceStatic()
    private val weatherService = WeatherServiceApixu()
    private val nearbyPlacesService = NearbyPlacesServiceGoogle()
    private val bestOptionStrategy = TresholdChooseActivityStrategy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_loading)

        launch(UI) {

            loadingText.text = getString(string.location_loading)

            val location = getLocation()

            delay(200)
            loadingText.text = getString(string.forecast_loading)

            val weather = withContext(CommonPool) { weatherService.getEveningWeather(location) }

            delay(800)
            loadingText.text = getString(string.activities_loading)

            val activities = getActivities(location)

            delay(400)
            loadingText.text = getString(string.choosing_best_loading)

            val best_activities = bestOptionStrategy.chooseBestActivity(weather, activities)

            delay(200)
            start(best_activities, activities)
        }
    }

    private suspend fun getLocation() : Location = suspendCoroutine { cont ->
        SmartLocation.with(this).location().oneFix().start { location ->
            cont.resume(location)
        }
    }

    private suspend fun getActivities(location : Location) : List<Activity> {

        val moviesDfr = async(CommonPool) { tvProgramService.getTodaysMovies() }
        val placesDfr = async(CommonPool) { nearbyPlacesService.getNearbyPlaces(location) }

        val activities = mutableListOf<Activity>()
        activities.addAll(moviesDfr.await())
        activities.addAll(placesDfr.await())

        return activities
    }

    private fun start(best_activities : Triple<Activity, Activity, Activity>, activities : List<Activity>) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(EXTRA_ACTIVITIES, best_activities)
        // TODO intent.putExtra(EXTRA_ACTIVITIES, activities)
        startActivity(intent)
    }
}
