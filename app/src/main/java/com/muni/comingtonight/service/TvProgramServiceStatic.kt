package com.muni.comingtonight.service

import com.muni.comingtonight.model.Show

class TvProgramServiceStatic : TvProgramService {

    private val movies = listOf(
            Show("Lord of The ring"),
            Show("Simpsons XXVII."),
            Show("Game of Thronmes")
    )

    override fun getTodaysMovies(): List<Show> {
        return movies
    }

}