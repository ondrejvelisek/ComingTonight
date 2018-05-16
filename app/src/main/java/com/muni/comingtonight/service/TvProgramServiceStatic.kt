package com.muni.comingtonight.service

import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.model.Category
import java.net.URI

class TvProgramServiceStatic : TvProgramService {

    private val IMAGE_URI = URI("https://creativescreenwriting.com/wp-content/uploads/2016/10/The-Dark-Knight-Joker-featured.jpg")

    private val movies = listOf(
            Activity("Taggart", 7.2, Category.HOME, null, URI("https://ia.media-imdb.com/images/M/MV5BMTExODkwMDczODZeQTJeQWpwZ15BbWU3MDI3OTE3MzE@._V1_SX300.jpg")),
            Activity("Simpsons XXVII.", 6.0, Category.HOME, null, IMAGE_URI),
            Activity("Game of Thronmes", 7.0, Category.HOME, null, IMAGE_URI)
    )

    override suspend fun getTodaysMovies(): List<Activity> {
        return movies
    }

}