package com.openclassrooms.realestatemanager.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.openclassrooms.realestatemanager.database.repositories.PhotoRepository
import com.openclassrooms.realestatemanager.model.Photo

class PhotoViewModel (application: Application) : ViewModel() {

    private var photoRepository: PhotoRepository = PhotoRepository(application)
    private var allPhoto: LiveData<List<Photo>> = photoRepository.getAllPhoto()



    fun getAllPhoto(): LiveData<List<Photo>> {
        return allPhoto
    }
    fun getPhotoByPropertyId(propertyId: Long): LiveData<List<Photo>>{
        return photoRepository.getPhotoByPropertyId(propertyId )
    }

    fun getPhoto(photoId: Long): LiveData<Photo> = photoRepository.getPhoto(photoId)

    fun createPhoto(photo: Photo){
        photoRepository.createPhoto(photo)
    }
    fun addAllPhotos(photos: List<Photo>){
        photoRepository.insertPhotos(photos)
    }
    fun updatePhoto(photo: Photo){
        photoRepository.updatePhoto(photo)
    }


}