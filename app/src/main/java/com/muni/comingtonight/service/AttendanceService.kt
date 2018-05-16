package com.muni.comingtonight.service

import com.muni.comingtonight.model.Activity

interface AttendanceService {

    fun attend(activity : Activity)

    fun unattend(activity : Activity)

    fun attended(activity : Activity) : Boolean
}