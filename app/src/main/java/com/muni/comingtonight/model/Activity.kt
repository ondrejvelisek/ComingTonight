package com.muni.comingtonight.model

import android.location.Location
import java.net.URL

data class Activity(
        val name: String,
        val rating: Double,
        val category: Category
//      val location: Location,
//      val imageUrl: URL
)
