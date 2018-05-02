package com.muni.comingtonight.service

import android.location.Location
import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.model.Category

class NearbyPlacesServiceStatic : NearbyPlacesService {

    private val places = listOf(
            Activity("Kravi Hora", 3.9, Category.NATURE),
            Activity("Spilberk", 4.5, Category.CITY),
            Activity("Lužánky Park", 4.4, Category.NATURE)
    )

    override fun getNearbyPlaces(location: Location): List<Activity> {
        return places
    }

}