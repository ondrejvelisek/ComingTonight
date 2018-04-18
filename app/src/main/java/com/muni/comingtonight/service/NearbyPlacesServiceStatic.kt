package com.muni.comingtonight.service

import android.location.Location
import com.muni.comingtonight.model.Place

class NearbyPlacesServiceStatic : NearbyPlacesService {

    private val places = listOf(
            Place("Kravi Hora", 3.9),
            Place("Spilberk", 4.5),
            Place("Lužánky Park", 4.4)
    )

    override fun getNearbyPlaces(location: Location): List<Place> {
        return places
    }

}