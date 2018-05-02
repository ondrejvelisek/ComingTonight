package com.muni.comingtonight.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * https://api.apixu.com/v1/
 */
interface ApixuApi {

    @GET("forecast.json")
    fun todaysForecast(
            @Query("q") location: String,
            @Query("key") apiKey: String
    ): Call<JsonObject>

}
