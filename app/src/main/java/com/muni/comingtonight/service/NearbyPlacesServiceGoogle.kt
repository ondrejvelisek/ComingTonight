package com.muni.comingtonight.service

import android.location.Location
import com.muni.comingtonight.model.Place

/**
 * Implementation based on Google Places API service https://developers.google.com/places/web-service/search
 * We registered following api key
 * apikey=AIzaSyBte43rrniMxyhl5H0UYT7gYPlIWWjfLco
 */
class NearbyPlacesServiceGoogle : NearbyPlacesService {

    override fun getNearbyPlaces(location: Location): List<Place> {
        TODO("not implemented")
    }

}