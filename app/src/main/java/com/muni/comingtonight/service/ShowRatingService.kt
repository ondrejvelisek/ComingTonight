package com.muni.comingtonight.service

import java.net.URI

interface ShowRatingService {

    suspend fun getRating(title: String) : Pair<Double, URI>

}