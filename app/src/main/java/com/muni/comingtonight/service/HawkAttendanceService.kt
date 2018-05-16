package com.muni.comingtonight.service

import android.content.Context
import com.muni.comingtonight.model.Activity
import com.orhanobut.hawk.Hawk

const val STORAGE_ATTENDED = "STORAGE_ATTENDED"

class HawkAttendanceService : AttendanceService {
    constructor(context : Context) {
        Hawk.init(context).build()
    }

    // TODO make class singleton / lock as static variable
    val attendLock = Any()

    override fun attend(activity : Activity) {
        synchronized(attendLock) {
            if (!Hawk.contains(STORAGE_ATTENDED)) {
                Hawk.put(STORAGE_ATTENDED, HashSet<String>())
            }
            val attend : MutableSet<String> = Hawk.get(STORAGE_ATTENDED)
            attend.add(activity.name)
            Hawk.put(STORAGE_ATTENDED, attend)
        }
    }

    override fun unattend(activity : Activity) {
        synchronized(attendLock) {
            if (!Hawk.contains(STORAGE_ATTENDED)) {
                Hawk.put(STORAGE_ATTENDED, HashSet<String>())
            }
            val attend : MutableSet<String> = Hawk.get(STORAGE_ATTENDED)
            attend.remove(activity.name)
            Hawk.put(STORAGE_ATTENDED, attend)
        }
    }

    override fun attended(activity : Activity) : Boolean {
        synchronized(attendLock) {
            if (!Hawk.contains(STORAGE_ATTENDED)) {
                Hawk.put(STORAGE_ATTENDED, HashSet<String>())
            }
            println((Hawk.contains(STORAGE_ATTENDED)).toString())
            println((Hawk.get(STORAGE_ATTENDED) as Set<String>?).toString())
            val attend : MutableSet<String> = Hawk.get(STORAGE_ATTENDED)
            return attend.contains(activity.name)
        }
    }


}