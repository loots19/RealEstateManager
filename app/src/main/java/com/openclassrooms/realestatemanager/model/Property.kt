package com.openclassrooms.realestatemanager.model

import android.content.ContentValues
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property")
data class Property(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "property_id")
        var id: Long = 0,
        @ColumnInfo(name = "city")
        var city: String = "",
        @ColumnInfo(name = "price")
        var price: String = "",
        @ColumnInfo(name = "type")
        var type: String = "",
        @ColumnInfo(name = "surface")
        var surface: Int = 0,
        @ColumnInfo(name = "nbr_room")
        var nbrRoom: Int = 0,
        @ColumnInfo(name = "nbr_bathroom")
        var nbrBathRoom: Int,
        @ColumnInfo(name = "nbr_bedroom")
        var nbrBedRoom: Int = 0,
        @ColumnInfo(name = "address")
        var address: String = "",
        @ColumnInfo(name = "date_entry")
        var dateEntry: String ="",
        @ColumnInfo(name = "photo_cover")
        var photoCover: String,
        @ColumnInfo(name = "description")
        var description: String,
        @ColumnInfo(name = "date_sale")
        var dateSale: String = "",
        @ColumnInfo(name = "property_lat")
        var propertyLat: Double? = null,
        @ColumnInfo(name = "property_lng")
        var propertyLng : Double? = null,
        @ColumnInfo(name = "agent_id")
        var agentId : Int = 0,
        @ColumnInfo(name = "agent_name")
        var agentName : String = ""
){

        companion object{
                fun fromContentValues(values: ContentValues) : Property{
                        val property = Property(0,"","","",0,0,0,0,"","","","","",0.0,0.0,0)
                        if(values.containsKey("property_id"))property.id = values.getAsLong("id")
                        if(values.containsKey("city"))property.city = values.getAsString("city")
                        if(values.containsKey("price"))property.price = values.getAsString("price")
                        if(values.containsKey("type"))property.type = values.getAsString("type")
                        if(values.containsKey("surface"))property.surface = values.getAsInteger("surface")
                        if(values.containsKey("nbr_room"))property.nbrRoom = values.getAsInteger("nbr_room")
                        if(values.containsKey("nbr_bathroom"))property.nbrBathRoom = values.getAsInteger("nbr_bathroom")
                        if(values.containsKey("nbr_bedroom"))property.nbrBedRoom = values.getAsInteger("nbr_bedroom")
                        if(values.containsKey("address"))property.address = values.getAsString("address")
                        if(values.containsKey("date_entry"))property.dateEntry = values.getAsString("date_entry")
                        if(values.containsKey("photo_cover"))property.photoCover = values.getAsString("photo_cover")
                        if(values.containsKey("description"))property.description = values.getAsString("description")
                        if(values.containsKey("date_sale"))property.dateSale = values.getAsString("date_sale")
                        if(values.containsKey("property_lat"))property.propertyLat = values.getAsDouble("property_lat")
                        if(values.containsKey("property_lng"))property.propertyLng = values.getAsDouble("property_lng")
                        if(values.containsKey("agent_id"))property.agentId = values.getAsInteger("agent_id")
                        if(values.containsKey("agent_name"))property.agentName = values.getAsString("agent_name")


                      return property
                }
        }
}