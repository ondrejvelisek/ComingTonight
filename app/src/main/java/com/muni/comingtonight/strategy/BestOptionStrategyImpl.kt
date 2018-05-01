package com.muni.comingtonight.strategy

import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.model.Category
import com.muni.comingtonight.model.Weather

class BestOptionStrategyImpl : BestOptionStrategy {

    override fun chooseBestActivity(weather: Weather, activities: List<Activity>): Activity {

        if (
                weather.precipitation < 0.05
                && weather.temperature > 5 && weather.temperature < 30
                && weather.wind < 100
        ) {
            // Perfect weather

            return activities
                    .filter { activity -> activity.category == Category.NATURE }
                    .reduce { acc, activity -> if (activity.rating > acc.rating) activity else acc }

        } else if (
                weather.precipitation > 0.5
                || weather.temperature < -5 || weather.temperature > 40
                || weather.wind > 300
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