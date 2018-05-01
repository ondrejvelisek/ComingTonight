package com.muni.comingtonight.service

import android.location.Location
import com.muni.comingtonight.api.GooglePlacesApi
import com.muni.comingtonight.model.Activity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Implementation based on Google Places API service https://developers.google.com/places/web-service/search
 * We registered following api key
 * apikey=AIzaSyBte43rrniMxyhl5H0UYT7gYPlIWWjfLco
 */
class NearbyPlacesServiceGoogle : NearbyPlacesService {

    override fun getNearbyPlaces(location: Location): List<Activity> {
        TODO("not implemented")

        val retrofit = Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val googlePlaces = retrofit.create(GooglePlacesApi::class.java)
    }

}