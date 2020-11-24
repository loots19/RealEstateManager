package com.openclassrooms.realestatemanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.openclassrooms.realestatemanager.model.Property

interface PropertyDao {

    @Insert
    fun addProperty(property: Property) : Long

    @Query("SELECT * FROM property")
    fun getAllProperty(): LiveData<List<Property>>

    @Query("SELECT * FROM property WHERE id = :propertyId")
    fun getProperty(propertyId : Long) : LiveData<Property>

    @Delete
    fun delete(property: Property): Int

    @Update
    fun update(property: Property): Int
}