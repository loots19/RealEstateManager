package com.openclassrooms.realestatemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property")
class Property(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var price: Double,
        var type: String,
        var surface: Int,
        var nbrRoom: Int,
        var nbrBathRoom: Int,
        var nbrBedRoom: Int,
        var address: String,
        var dateEntry: String,
        var photo: String,
        var description: String,
        var dateSale: String,
        var propertyLat: Double? = null,
        var propertyLng : Double? = null,
        var agentId : Int){
}