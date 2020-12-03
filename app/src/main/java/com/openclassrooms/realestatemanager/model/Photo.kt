package com.openclassrooms.realestatemanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class Photo(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "photo_id")
        var idPhoto: Int,
        @ColumnInfo(name = "photo_url")
        var urlPhoto: Int,
        var name: String,
        @ColumnInfo(name = "property_id")
        var propertyId: Long) {
}