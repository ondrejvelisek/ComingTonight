package com.muni.comingtonight.service

import com.muni.comingtonight.model.Show

class ShowRatingServiceStatic : ShowRatingService{

    override fun getRating(show: Show): Double {
        return 8.3
    }

}