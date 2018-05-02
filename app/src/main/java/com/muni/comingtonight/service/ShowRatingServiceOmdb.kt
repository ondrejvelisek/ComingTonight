package com.muni.comingtonight.service

/**
 * Implementation based on Omdb API service http://www.omdbapi.com/
 * We registered following api key
 * apikey=74457713
 */
class ShowRatingServiceOmdb : ShowRatingService{

    override suspend fun getRating(title: String): Double {
        TODO("Unimplemented yet")
    }

}