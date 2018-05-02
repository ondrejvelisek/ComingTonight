package com.muni.comingtonight.service

import com.muni.comingtonight.model.Activity

interface TvProgramService {

    fun getTodaysMovies() : List<Activity>

}