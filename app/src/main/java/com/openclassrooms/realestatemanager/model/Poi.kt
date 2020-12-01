package com.openclassrooms.realestatemanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class Poi (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "poi_id")
        var poiId: Int,
        @ColumnInfo(name = "name_poi")
        var namePoi: String,
        @ColumnInfo(name = "street_poi")
        var streetPoi: Int,
        @ColumnInfo(name = "photo_poi")
        var photoPoi: String,
        @ColumnInfo(name = "lat_poi")
        var latPoi: Double,
        @ColumnInfo(name = "lng_poi")
        var lngPoi: Double,
        @ColumnInfo(name = "property_id")
        var propertyId: Long


        )
{
}