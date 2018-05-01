package com.muni.comingtonight.service

import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.model.Category

class TvProgramServiceStatic : TvProgramService {

    private val movies = listOf(
            Activity("Lord of The ring", 8.9, Category.HOME),
            Activity("Simpsons XXVII.", 8.0, Category.HOME),
            Activity("Game of Thronmes", 7.3, Category.HOME)
    )

    override fun getTodaysMovies(): List<Activity> {
        return movies
    }

}