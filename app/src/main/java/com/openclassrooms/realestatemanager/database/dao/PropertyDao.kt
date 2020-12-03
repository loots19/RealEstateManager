package com.openclassrooms.realestatemanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.android.gms.common.internal.IGmsCallbacks
import com.openclassrooms.realestatemanager.model.Property

@Dao
interface PropertyDao {

    @Insert
    fun addProperty(property: Property) : Long

    @Query("SELECT * FROM property")
    fun getAllProperty(): LiveData<List<Property>>

    @Query("SELECT * FROM property WHERE property_id = :id")
    fun getProperty(id : Long) : LiveData<Property>

    @Delete
    fun delete(property: Property): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(property: Property): Int
}