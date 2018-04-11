package com.muni.comingtonight.service

import com.muni.comingtonight.model.Show

interface ShowRatingService {

    fun getRating(show: Show) : Double

}