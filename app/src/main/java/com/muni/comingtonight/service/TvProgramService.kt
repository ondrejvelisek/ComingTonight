package com.muni.comingtonight.service

import com.muni.comingtonight.model.Show

interface TvProgramService {

    fun getTodaysMovies() : List<Show>

}