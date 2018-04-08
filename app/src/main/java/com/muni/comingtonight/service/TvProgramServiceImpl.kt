package com.muni.comingtonight.service

import com.muni.comingtonight.model.Movie

class TvProgramServiceImpl : TvProgramService {

    private val movies = listOf(
            Movie("Lord of The ring", 2001, "J.R. Tolkien"),
            Movie("Simpsons XXVII.", 2013, "Indiana Jones"),
            Movie("Game of Thronmes", 1993, "Mrs. Brown")
    )

    override fun getTodaysMovies(): List<Movie> {
        return movies
    }

}