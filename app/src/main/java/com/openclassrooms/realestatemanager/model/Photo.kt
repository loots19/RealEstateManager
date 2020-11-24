package com.openclassrooms.realestatemanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "idPhoto")
        var idPhoto: Int,
        @ColumnInfo(name = "urlPhoto")
        var urlPhoto: String,
        var name: String,
        @ColumnInfo(name = "propertyId")
        var propertyId: Long) {
}