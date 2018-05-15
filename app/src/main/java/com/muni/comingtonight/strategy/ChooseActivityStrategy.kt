package com.muni.comingtonight.strategy

import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.model.Weather

interface ChooseActivityStrategy {

    fun chooseBestActivity(weather: Weather, activities: List<Activity>) : Activity

}