package com.muni.comingtonight.service

import com.muni.comingtonight.api.OmdbApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.retrofit.await
import java.net.URI

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

    override suspend fun getRating(title: String): Double {
        val movieInfo = omdbApi.getMovieInfo(title, "74457713"
        ).await()

        val response = movieInfo
                .getAsJsonPrimitive("Response")
                .asString

        if (response == "True")
        {
            val  rating = movieInfo
                    .getAsJsonPrimitive("imdbRating")
                    .asDouble
            /*
            //Poster example: http://ia.media-imdb.com/images/M/MV5BZTUzNGIyM2EtZTY1MC00NzZjLTk0MjgtN2NhYWQ5MzFhZGRkXkEyXkFqcGdeQXVyMjIxMzMyMQ@@._V1_SX300.jpg

            val poster = URI(movieInfo
                    .getAsJsonPrimitive("Poster")
                    .asString)
            */

            return rating
            //return Pair(rating, poster)
        }
        else
        {
            return 0.0
            //return Pair(0.0, "")
        }
    }
}

