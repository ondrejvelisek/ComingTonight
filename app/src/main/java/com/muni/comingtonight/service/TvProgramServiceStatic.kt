package com.muni.comingtonight.service

import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.model.Category
import java.net.URI

class TvProgramServiceStatic : TvProgramService {

    private val IMAGE_URI = URI("https://creativescreenwriting.com/wp-content/uploads/2016/10/The-Dark-Knight-Joker-featured.jpg")

    private val movies = listOf(
            Activity("Lord of The ring", 8.9, Category.HOME, IMAGE_URI),
            Activity("Simpsons XXVII.", 8.0, Category.HOME, IMAGE_URI),
            Activity("Game of Thronmes", 7.3, Category.HOME, IMAGE_URI)
    )

    override suspend fun getTodaysMovies(): List<Activity> {
        return movies
    }

}