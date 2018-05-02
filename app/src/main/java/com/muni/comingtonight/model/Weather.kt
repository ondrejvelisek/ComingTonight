package com.muni.comingtonight.model

data class Weather(
        val locationName: String,
        val precipitation: Double,
        val temperature: Double,
        val wind: Double
)