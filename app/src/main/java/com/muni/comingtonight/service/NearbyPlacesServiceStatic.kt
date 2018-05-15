package com.muni.comingtonight.service

import android.location.Location
import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.model.Category
import java.net.URI

class NearbyPlacesServiceStatic : NearbyPlacesService {

    private val IMAGE_URI = URI("https://images.pexels.com/photos/257360/pexels-photo-257360.jpeg")

    private val places = listOf(
            Activity("Kravi Hora", 3.9, Category.OUTDOOR, IMAGE_URI),
            Activity("Spilberk", 4.5, Category.INDOOR, IMAGE_URI),
            Activity("Lužánky Park", 4.4, Category.OUTDOOR, IMAGE_URI)
    )

    override suspend fun getNearbyPlaces(location: Location): List<Activity> {
        return places
    }

}