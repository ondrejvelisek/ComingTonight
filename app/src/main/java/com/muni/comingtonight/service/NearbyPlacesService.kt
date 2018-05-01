package com.muni.comingtonight.service

import android.location.Location
import com.muni.comingtonight.model.Activity

/**
 * Created by ondrejvelisek on 18.4.18.
 */

interface NearbyPlacesService {

    fun getNearbyPlaces(location: Location) : List<Activity>

}