package com.muni.comingtonight.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.muni.comingtonight.R
import com.muni.comingtonight.api.GooglePlacesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val googlePlaces = retrofit.create(GooglePlacesApi::class.java)

        val placesCall = googlePlaces.nearbySearch("49.211210,16.577599", 4000, "park", "AIzaSyBte43rrniMxyhl5H0UYT7gYPlIWWjfLco")

        placesCall.enqueue(object : Callback<Map<String, Any>> {
            override fun onResponse(call: Call<Map<String, Any>>?, response: Response<Map<String, Any>>?) {
                println(response?.body())
            }
            override fun onFailure(call: Call<Map<String, Any>>?, t: Throwable?) {
                // Error handling
            }
        })

    }
}
