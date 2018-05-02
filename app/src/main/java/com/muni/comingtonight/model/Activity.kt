package com.muni.comingtonight.model

import java.net.URI

data class Activity(
        val name: String,
        val rating: Double,
        val category: Category,
//      val location: Location,
        val imageUri: URI
)
