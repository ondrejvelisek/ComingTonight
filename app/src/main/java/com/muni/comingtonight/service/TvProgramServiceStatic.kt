package com.muni.comingtonight.service

import com.muni.comingtonight.model.Show

class TvProgramServiceStatic : TvProgramService {

    private val movies = listOf(
            Show("Lord of The ring", 8.9),
            Show("Simpsons XXVII.", 8.0),
            Show("Game of Thronmes", 7.3)
    )

    override fun getTodaysMovies(): List<Show> {
        return movies
    }

}