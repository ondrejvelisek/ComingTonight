package com.muni.comingtonight.service

import com.muni.comingtonight.api.OmdbApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.retrofit.await

/**
 * Implementation based on Omdb API service http://www.omdbapi.com/
 * We registered following api key
 * apikey=74457713
 */
class ShowRatingServiceOmdb : ShowRatingService{

    val omdbApi = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OmdbApi::class.java)

    override suspend fun getRating(title: String): Double
    {
        val movieInfo = omdbApi.getMovieInfo(title,"74457713"
        ).await()

        println(movieInfo.toString())

        val  rating = movieInfo
                .getAsJsonObject("movie")
                .getAsJsonPrimitive("imdbRating")
                .asDouble
        /*
        val  poster = movieInfo
                .getAsJsonObject("movie")
                .getAsJsonPrimitive("imdbRating")
                .asString
        */
        if (rating > 0.0)
        {
            return rating
            //return Pair(rating, poster)
        }
        else
        {
            return 0.0
            //return Pair(0.0, poster)
        }

    }

}

