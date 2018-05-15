package com.muni.comingtonight.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi
{
    //@GET("info.json")
    fun getMovieInfo(
            @Query("t") title: String,
            @Query("key") apiKey: String
            ): Call<JsonObject>

}