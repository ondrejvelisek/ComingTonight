package com.muni.comingtonight.model

import android.location.Location
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable
import java.net.URI

data class Activity(
        val name: String,
        val rating: Double,
        val category: Category,
        @Transient var location: Location?,
        val imageUri: URI?
) : Serializable {

    private fun writeObject(oos : ObjectOutputStream) {
        oos.defaultWriteObject()
        oos.writeDouble(location?.latitude ?: 0.0)
        oos.writeDouble(location?.longitude ?: 0.0)
    }

    private fun readObject(ois : ObjectInputStream) {
        ois.defaultReadObject()
        val location = Location("INTERNAL")
        location.latitude = ois.readDouble()
        location.longitude = ois.readDouble()

        if (location.latitude == 0.0 && location.latitude == 0.0) {
            this.location = null
        } else {
            this.location = location
        }
    }
}
