package com.muni.comingtonight.strategy

import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.model.Category
import com.muni.comingtonight.model.Weather

class BestOptionStrategyImpl : BestOptionStrategy {

    override fun chooseBestActivity(weather: Weather, activities: List<Activity>): Activity {

        // TODO extract tresholds as instance field (constructor initialized)

        if (
                weather.precipitation < 0.1
                && weather.temperature > 5 && weather.temperature < 30
                && weather.wind < 30
        ) {
            // Perfect weather

            return activities
                    .filter { activity -> activity.category == Category.NATURE }
                    .reduce { acc, activity -> if (activity.rating > acc.rating) activity else acc }

        } else if (
                weather.precipitation > 8
                || weather.temperature < -5 || weather.temperature > 40
                || weather.wind > 100
        ) {
            // Terrible weather

            return activities
                    .filter { activity -> activity.category == Category.HOME }
                    .reduce { acc, activity -> if (activity.rating > acc.rating) activity else acc }

        } else {
            // Moderate weather

            return activities
                    .filter { activity -> activity.category == Category.CITY }
                    .reduce { acc, activity -> if (activity.rating > acc.rating) activity else acc }

        }

    }

}