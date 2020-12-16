package com.openclassrooms.realestatemanager.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(foreignKeys = [ForeignKey(
        entity = Property::class,
        parentColumns = ["id"],
        childColumns = ["property_id"],
        onDelete = CASCADE

)],
        indices = [Index(value = ["property_id"])])

data class Photo(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "photo_id") var idPhoto: Long,
        @ColumnInfo(name = "photo_url") var urlPhoto: String,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "property_id") var propertyId: Long) {


}