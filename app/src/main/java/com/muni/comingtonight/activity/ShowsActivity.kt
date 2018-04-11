package com.muni.comingtonight.activity

import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.muni.comingtonight.R
import com.muni.comingtonight.model.Show
import com.muni.comingtonight.service.ShowRatingServiceStatic
import com.muni.comingtonight.service.TvProgramServiceStatic
import com.muni.comingtonight.service.WeatherServiceStatic
import io.nlopez.smartlocation.OnLocationUpdatedListener
import io.nlopez.smartlocation.SmartLocation
import kotlinx.android.synthetic.main.activity_shows.*
import kotlinx.android.synthetic.main.activity_shows.view.*

class ShowsActivity : AppCompatActivity() {

    private val tvProgramService = TvProgramServiceStatic()
    private val showRatingService = ShowRatingServiceStatic()
    private val weatherService = WeatherServiceStatic()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shows)

        val shows = tvProgramService.getTodaysMovies()
        var bestShow = shows.first()
        shows.forEach { show ->
            if (showRatingService.getRating(show) > showRatingService.getRating(bestShow)) {
                bestShow = show
            }
        }

        //movies_list_view.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tvProgramService.getTodaysMovies())

        movie_text_view.text = bestShow.toString()

        SmartLocation.with(this).location()
                .start { location ->
                    val weather = weatherService.getEveningWeather(location)

                    weather_text_view.text = weather.toString()
                }

    }
}
