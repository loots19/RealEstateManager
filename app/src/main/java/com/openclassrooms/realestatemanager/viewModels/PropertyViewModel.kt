package com.openclassrooms.realestatemanager.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.openclassrooms.realestatemanager.database.repositories.PropertyRepository
import com.openclassrooms.realestatemanager.model.Property

class PropertyViewModel(application: Application) : ViewModel() {

    private var propertyRepository: PropertyRepository = PropertyRepository(application)
    private var allProperties: LiveData<List<Property>> = propertyRepository.getAllProperty()


    fun getAllProperty(): LiveData<List<Property>> {
        return allProperties

    }

    fun getProperty(propertyId: Long): LiveData<Property> =
            propertyRepository.getProperty(propertyId)

    fun createProperty(property: Property) {
        propertyRepository.createProperty(property)
    }
    fun udpdateProperty(property: Property){
        propertyRepository.updateProperty(property)
    }

}