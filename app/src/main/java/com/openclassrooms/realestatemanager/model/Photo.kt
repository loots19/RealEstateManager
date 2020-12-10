package com.openclassrooms.realestatemanager.model

import androidx.room.*

@Entity(tableName = "photo", foreignKeys = [ForeignKey(
        entity = Property::class,
        parentColumns = ["property_id"],
        childColumns = ["photo_id"]
)],
        indices = [Index(value = ["property_id"])])
data class Photo(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "photo_id")
        var idPhoto: Int,
        @ColumnInfo(name = "photo_url")
        var urlPhoto: String,
        @ColumnInfo(name = "name")
        var name: String,
        @ColumnInfo(name = "property_id")
        var propertyId: Long) {


}