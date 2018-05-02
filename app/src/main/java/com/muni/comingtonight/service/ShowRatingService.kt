package com.muni.comingtonight.service

interface ShowRatingService {

    suspend fun getRating(title: String) : Double

}