package com.openclassrooms.realestatemanager.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.openclassrooms.realestatemanager.model.Photo

class Converters {

    @TypeConverter
    fun listToJson(value: List<Photo>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Photo>? {
        val objects = Gson().fromJson(value, Array<Photo>::class.java) as Array<Photo>
        return objects.toList()
    }
}