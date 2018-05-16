package com.muni.comingtonight.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.muni.comingtonight.R
import com.muni.comingtonight.model.Activity
import kotlinx.android.synthetic.main.activity_main.*
import com.squareup.picasso.Picasso
import android.content.Intent
import android.net.Uri
import android.view.View.INVISIBLE
import com.muni.comingtonight.service.HawkAttendanceService

const val EXTRA_BEST_ACTIVITY = "EXTRA_BEST_ACTIVITY"
const val EXTRA_ACTIVITIES = "EXTRA_ACTIVITIES"

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

        if (activity.location == null) {
            navigateButton.visibility = INVISIBLE
        } else {
            navigateButton.setOnClickListener {
                val mapIntentUri = Uri.parse("google.navigation:q=%f,%f".format(
                        activity.location?.latitude,
                        activity.location?.longitude
                ))
                val mapIntent = Intent(Intent.ACTION_VIEW, mapIntentUri)
                startActivity(mapIntent)
            }
        }

        val attendanceService = HawkAttendanceService(this)

        println(attendanceService.attended(activity))

        attendedCheckbox.isChecked = attendanceService.attended(activity)

        attendedCheckbox.setOnCheckedChangeListener { _, attended ->
            if (attended) {
                attendanceService.attend(activity)
            } else {
                attendanceService.unattend(activity)
            }
        }
    }

}
