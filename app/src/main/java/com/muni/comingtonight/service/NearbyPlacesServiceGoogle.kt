package com.muni.comingtonight.service

import android.location.Location
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.muni.comingtonight.api.GooglePlacesApi
import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.model.Category
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.retrofit.await
import java.net.URI

/**
 * Implementation based on Google Places API service https://developers.google.com/places/web-service/search
 * We registered following api key
 * apikey=AIzaSyBte43rrniMxyhl5H0UYT7gYPlIWWjfLco
 */
class NearbyPlacesServiceGoogle : NearbyPlacesService {

    private val googlePlacesApi = Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/place/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GooglePlacesApi::class.java)

    override suspend fun getNearbyPlaces(location: Location): List<Activity> {

        val naturalFeaturesCall = googlePlacesApi.nearbySearch(
                "%f,%f".format(location.latitude, location.longitude),
                10000,
                "natural_feature",
                "AIzaSyBte43rrniMxyhl5H0UYT7gYPlIWWjfLco"
        )

        val parksCall = googlePlacesApi.nearbySearch(
                "%f,%f".format(location.latitude, location.longitude),
                10000,
                "park",
                "AIzaSyBte43rrniMxyhl5H0UYT7gYPlIWWjfLco"
        )

        val museumsCall = googlePlacesApi.nearbySearch(
                "%f,%f".format(location.latitude, location.longitude),
                10000,
                "museum",
                "AIzaSyBte43rrniMxyhl5H0UYT7gYPlIWWjfLco"
        )

        val artGalleriesCall = googlePlacesApi.nearbySearch(
                "%f,%f".format(location.latitude, location.longitude),
                10000,
                "art_gallery",
                "AIzaSyBte43rrniMxyhl5H0UYT7gYPlIWWjfLco"
        )

        val places = mutableListOf<Activity>()

        places.addAll(naturalFeaturesCall.await().getAsJsonArray("results")
                .map { place -> extractActivity(place.asJsonObject, Category.NATURE) }
        )
        places.addAll(parksCall.await().getAsJsonArray("results")
                .map { place -> extractActivity(place.asJsonObject, Category.NATURE) }
        )
        places.addAll(museumsCall.await().getAsJsonArray("results")
                .map { place -> extractActivity(place.asJsonObject, Category.CITY) }
        )
        places.addAll(artGalleriesCall.await().getAsJsonArray("results")
                .map { place -> extractActivity(place.asJsonObject, Category.CITY) }
        )

        return places

    }

    private fun containsType(place: JsonObject, type: String) : Boolean {
        return place.getAsJsonArray("types").contains(JsonPrimitive(type))

    }


    private fun extractActivity(place: JsonObject, category: Category) : Activity {
        return Activity(
                extractName(place.asJsonObject),
                extractRating(place.asJsonObject),
                category,
                extractImageUri(place.asJsonObject)
        )
    }

    private fun extractCategory(place: JsonObject) : Category {
        if (containsType(place, "natural_feature")
                || containsType(place.asJsonObject, "zoo")
                || containsType(place.asJsonObject, "park")) {
            return Category.NATURE
        } else {
            return Category.CITY
        }
    }

    private fun extractName(place: JsonObject) : String {
        return place.getAsJsonPrimitive("name").asString
    }

    private fun extractRating(place: JsonObject) : Double {
        if (!place.has("rating")) {
            return 0.0
        }
        return place.getAsJsonPrimitive("rating").asDouble
    }

    private fun extractImageUri(place: JsonObject) : URI? {
        if (!place.has("photos")) {
            return null
        }
        if (place.getAsJsonArray("photos").size() == 0) {
            return null
        }
        if (!place.getAsJsonArray("photos").get(0).asJsonObject.has("photo_reference")) {
            return null
        }
        return constructImageUri(place
                .getAsJsonArray("photos")
                .get(0).asJsonObject
                .getAsJsonPrimitive("photo_reference").asString)
    }

    private fun constructImageUri(photoReference: String) : URI {
        return URI(
                "https://maps.googleapis.com/maps/api/place/photo?photoreference=%s&maxwidth=%d&key=%s"
                        .format(photoReference, 1000, "AIzaSyBte43rrniMxyhl5H0UYT7gYPlIWWjfLco")
        )
    }

}