package com.muni.comingtonight.service

class ShowRatingServiceStatic : ShowRatingService{

    override suspend fun getRating(title: String): Double {
        return 8.3
    }

}