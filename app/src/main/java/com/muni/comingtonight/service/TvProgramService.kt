package com.muni.comingtonight.service

import com.muni.comingtonight.model.Movie

interface TvProgramService {

    fun getTodaysMovies() : List<Movie>

}