package com.muni.comingtonight.activity

import android.content.ClipData
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.muni.comingtonight.R
import com.muni.comingtonight.model.Activity
import kotlinx.android.synthetic.main.activity_main.*
import com.squareup.picasso.Picasso
import android.content.Intent
import android.net.Uri
import android.support.design.internal.BottomNavigationItemView
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import com.muni.comingtonight.service.HawkAttendanceService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val best_activities = intent.getSerializableExtra(EXTRA_ACTIVITIES) as Triple<Activity, Activity, Activity>
        var activity: Activity = best_activities.first

        activityName.text = activity.name
        activityRating.text = activity.rating.toString()

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

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.action_favorites -> {
                            activity = best_activities.first
                            activityName.text = activity.name
                            activityRating.text = activity.rating.toString()

                            if (activity.imageUri != null) {
                                Picasso.with(baseContext)
                                        .load(activity.imageUri?.toString())
                                        .into(posterImage)

                            }
                            if (activity.location == null) {
                                navigateButton.visibility = INVISIBLE
                            } else {
                                navigateButton.visibility = View.VISIBLE
                                navigateButton.setOnClickListener {
                                    val mapIntentUri = Uri.parse("google.navigation:q=%f,%f".format(
                                            activity.location?.latitude,
                                            activity.location?.longitude
                                    ))
                                    val mapIntent = Intent(Intent.ACTION_VIEW, mapIntentUri)
                                    startActivity(mapIntent)
                                }
                            }
                        }
                        R.id.action_favorites1 -> {
                            activity = best_activities.second
                            activityName.text = activity.name
                            activityRating.text = activity.rating.toString()

                            if (activity.imageUri != null) {
                                Picasso.with(baseContext)
                                        .load(activity.imageUri?.toString())
                                        .into(posterImage)

                            }
                            if (activity.location == null) {
                                navigateButton.visibility = INVISIBLE
                            } else {
                                navigateButton.visibility = View.VISIBLE
                                navigateButton.setOnClickListener {
                                    val mapIntentUri = Uri.parse("google.navigation:q=%f,%f".format(
                                            activity.location?.latitude,
                                            activity.location?.longitude
                                    ))
                                    val mapIntent = Intent(Intent.ACTION_VIEW, mapIntentUri)
                                    startActivity(mapIntent)
                                }
                            }
                        }

                        R.id.action_favorites2 -> {
                            activity = best_activities.third
                            activityName.text = activity.name
                            activityRating.text = activity.rating.toString()

                            if (activity.imageUri != null) {
                                Picasso.with(baseContext)
                                        .load(activity.imageUri?.toString())
                                        .into(posterImage)

                            }
                            if (activity.location == null) {
                                navigateButton.visibility = INVISIBLE
                            } else {
                                navigateButton.visibility = View.VISIBLE
                                navigateButton.setOnClickListener {
                                    val mapIntentUri = Uri.parse("google.navigation:q=%f,%f".format(
                                            activity.location?.latitude,
                                            activity.location?.longitude
                                    ))
                                    val mapIntent = Intent(Intent.ACTION_VIEW, mapIntentUri)
                                    startActivity(mapIntent)
                                }
                            }
                        }
                    }
                    return@setOnNavigationItemSelectedListener true
                }

    }
}
