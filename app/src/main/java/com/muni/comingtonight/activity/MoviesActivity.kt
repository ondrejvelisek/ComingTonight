package com.muni.comingtonight.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.muni.comingtonight.R
import com.muni.comingtonight.service.TvProgramService
import com.muni.comingtonight.service.TvProgramServiceImpl
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {

    private val tvProgramService = TvProgramServiceImpl();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        movies_list_view.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tvProgramService.getTodaysMovies())
    }
}
