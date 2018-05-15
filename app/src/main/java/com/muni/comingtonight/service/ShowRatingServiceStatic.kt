package com.muni.comingtonight.service

import java.net.URI

class ShowRatingServiceStatic : ShowRatingService{

    override suspend fun getRating(title: String): Pair<Double, URI> {
        return Pair(8.3, URI("http://ia.media-imdb.com/images/M/MV5BZTUzNGIyM2EtZTY1MC00NzZjLTk0MjgtN2NhYWQ5MzFhZGRkXkEyXkFqcGdeQXVyMjIxMzMyMQ@@._V1_SX300.jpg"))
    }

}