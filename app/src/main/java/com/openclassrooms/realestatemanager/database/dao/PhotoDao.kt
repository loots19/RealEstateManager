package com.openclassrooms.realestatemanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.openclassrooms.realestatemanager.model.Photo

@Dao
interface PhotoDao {

    @Insert
    fun addPhoto(photo: Photo)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPhotos(photos : List<Photo>)


    @Query("SELECT * FROM photo WHERE property_id = :propertyId")
    fun getAllPhotoByProperty(propertyId: Long): LiveData<List<Photo>>

    @Query("SELECT * FROM photo")
    fun getAllPhotos() : LiveData<List<Photo>>


    @Query("SELECT * FROM photo WHERE photo_id = :id")
    fun getPhoto(id: Long): LiveData<Photo>


    @Delete
    fun delete(photo: Photo): Int


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(photo: Photo): Int

}