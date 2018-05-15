package com.muni.comingtonight.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.muni.comingtonight.R
import com.muni.comingtonight.model.Activity
import kotlinx.android.synthetic.main.activity_main.*
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activity: Activity = intent.getSerializableExtra(EXTRA_BEST_ACTIVITY) as Activity

        activityName.text = activity.name

        if (activity.imageUri != null) {
            Picasso.with(baseContext)
                    .load(activity.imageUri?.toString())
                    .into(posterImage)

        }
    }
}
