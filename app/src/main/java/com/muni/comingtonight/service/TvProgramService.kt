package com.muni.comingtonight.service

import com.muni.comingtonight.model.Activity

interface TvProgramService {

    suspend fun getTodaysMovies() : List<Activity>

}