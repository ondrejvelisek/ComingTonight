package com.muni.comingtonight.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * https://api.apixu.com/v1/
 */
interface CTApi {

    @GET("forecast.json")
    fun todaysProgram(
            @Query("date") date: String,
            @Query("channel") channel: String
    ): Call<String>

}
