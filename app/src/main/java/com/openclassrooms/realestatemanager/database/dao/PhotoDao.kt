package com.openclassrooms.realestatemanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.openclassrooms.realestatemanager.model.Photo

@Dao
interface PhotoDao {

    @Insert
    fun addPhoto(photo: Photo): Long

    @Query("SELECT * FROM photo")
    fun getAllPhoto(): LiveData<List<Photo>>

    @Query("SELECT * FROM photo WHERE photo_id = :id")
    fun getPhoto(id: Long): LiveData<Photo>

    @Delete
    fun delete(photo: Photo): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(photo: Photo): Int
}