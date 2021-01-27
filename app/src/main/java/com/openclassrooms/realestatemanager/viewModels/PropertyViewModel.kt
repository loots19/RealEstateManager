package com.openclassrooms.realestatemanager.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.openclassrooms.realestatemanager.database.repositories.PropertyRepository
import com.openclassrooms.realestatemanager.model.Photo
import com.openclassrooms.realestatemanager.model.Poi
import com.openclassrooms.realestatemanager.model.PoiResult
import com.openclassrooms.realestatemanager.model.Property
import java.util.*


class PropertyViewModel(propertyRepository: PropertyRepository, application: Application) : ViewModel() {

    private var propertyRepository: PropertyRepository = PropertyRepository(application)
    private var allProperties: LiveData<List<Property>> = propertyRepository.getAllProperty()
    private val getPlace = MutableLiveData<GetPlace>()
    private val poi:LiveData<List<Poi>>

    init {
        this.propertyRepository = propertyRepository
        poi = Transformations.switchMap(getPlace) { input ->
            propertyRepository.poiList(input.location, input.radius)
        }
    }


    fun setPlace(location: String, radius: Int) {
        val update = GetPlace(location, radius)
        if (Objects.equals(getPlace.value, update)) {
            return
        }
        getPlace.value = update
    }

    internal class GetPlace(location: String?, radius: Int) {
        val location: String = location?.trim { it <= ' ' } ?: null.toString()
        var radius: Int = 0

        init {
            this.radius = radius

        }
    }

    fun getPoiList(): LiveData<List<Poi>> {
        return poi

    }


    fun getAllProperty(): LiveData<List<Property>> {
        return allProperties

    }

    fun getProperty(propertyId: Long): LiveData<Property> =
            propertyRepository.getProperty(propertyId)

    fun createProperty(property: Property, photos: List<Photo>) {
        val long = propertyRepository.createProperty(property)
        for (p in photos) {
            p.propertyId = long
        }


    }

    fun updateProperty(property: Property) {
        propertyRepository.updateProperty(property)
    }



}