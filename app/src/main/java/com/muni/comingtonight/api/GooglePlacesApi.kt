package com.muni.comingtonight.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * https://maps.googleapis.com/maps/api/place/
 */
interface GooglePlacesApi {

    @GET("nearbysearch/json")
    fun nearbySearch(
            @Query("location") location: String,
            @Query("radius") radius: Int,
            @Query("type") type: String,
            @Query("key") apiKey: String
        ): Call<Map<String, Any>>
}
