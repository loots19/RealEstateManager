package com.openclassrooms.realestatemanager.database.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.openclassrooms.realestatemanager.database.AppDatabase
import com.openclassrooms.realestatemanager.database.dao.PhotoDao
import com.openclassrooms.realestatemanager.model.Photo

class PhotoRepository (application: Application){

    private var photoDao: PhotoDao
    private var allPhoto: LiveData<List<Photo>>
    private var db = AppDatabase.getDatabase(application.applicationContext)
    var long: Long = 0



    init{
        photoDao = db.photoDao()
        allPhoto = photoDao.getAllPhoto()
    }

    fun getAllPhoto(): LiveData<List<Photo>>{
        return allPhoto
    }

    fun getPhoto(photoId: Long): LiveData<Photo> = photoDao.getPhoto(photoId)

    fun createPhoto(photo: Photo): Long{
        long = photoDao.addPhoto(photo)
        return long
    }
    fun updatePhoto(photo: Photo){
        photoDao.update(photo)
    }

}