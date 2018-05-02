package com.muni.comingtonight.strategy

import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.model.Weather

interface BestOptionStrategy {

    fun chooseBestActivity(weather: Weather, activities: List<Activity>) : Activity

}