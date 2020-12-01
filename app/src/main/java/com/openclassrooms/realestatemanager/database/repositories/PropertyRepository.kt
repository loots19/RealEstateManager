package com.openclassrooms.realestatemanager.database.repositories

import android.app.Application
import android.util.EventLogTags
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.openclassrooms.realestatemanager.database.AppDatabase
import com.openclassrooms.realestatemanager.database.dao.PropertyDao
import com.openclassrooms.realestatemanager.model.Property

class PropertyRepository(application: Application) {

    private var propertyDao: PropertyDao
    private var allProperties: LiveData<List<Property>>
    private var db = AppDatabase.getDatabase(application.applicationContext)
    var long: Long = 0


    init {
        propertyDao = db.propertyDao()
        allProperties = propertyDao.getAllProperty()
    }


    fun getAllProperty(): LiveData<List<Property>> {
        return allProperties
    }

    fun getProperty(propertyId: Long): LiveData<Property> = propertyDao.getProperty(propertyId)

    fun createProperty(property: Property): Long {
        long = propertyDao.addProperty(property)
        //todo save in firebase.
        return long
    }

    fun updateProperty(property: Property) {
        propertyDao.update(property)
    }


    // --------------------------------
    // ----- COLLECTION REFERENCE -----
    // --------------------------------
    private fun getPropertyCollection(): CollectionReference {
        return FirebaseFirestore.getInstance().collection("property")
    }




}