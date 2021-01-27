package com.openclassrooms.realestatemanager.database.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.openclassrooms.realestatemanager.database.AppDatabase
import com.openclassrooms.realestatemanager.database.dao.PropertyDao
import com.openclassrooms.realestatemanager.model.Poi
import com.openclassrooms.realestatemanager.model.PoiResult
import com.openclassrooms.realestatemanager.model.Property
import com.openclassrooms.realestatemanager.retrofit.ApiRequest
import com.openclassrooms.realestatemanager.retrofit.RetrofitRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PropertyRepository(application: Application) {

    private var propertyDao: PropertyDao
    private var allProperties: LiveData<List<Property>>
    private var apiRequest: ApiRequest? = null
    private var db = AppDatabase.getDatabase(application.applicationContext)
    var long: Long = 0
    private var poiList = ArrayList<Poi>()


    init {
        propertyDao = db.propertyDao()
        allProperties = propertyDao.getAllProperty()
        apiRequest = RetrofitRequest.getApiRequest()


    }

    fun poiList(location: String, radius: Int): MutableLiveData<List<Poi>> {
        val newData = MutableLiveData<List<Poi>>()
        val apiRequest = RetrofitRequest.getApiRequest()

        apiRequest.getPoiFromProperty(location, radius).enqueue(object : Callback<PoiResult> {
            override fun onFailure(call: Call<PoiResult>, t: Throwable) {
                newData.value = null
            }

            override fun onResponse(call: Call<PoiResult>, response: Response<PoiResult>) {
                if (response.isSuccessful) {
                    val size = response.body()?.results?.size ?: 0

                    for (i in 0 until size) {

                        response.body()?.let { poiResult ->
                            val name = poiResult.results[i].types
                            val street = poiResult.results[i].vicinity
                            val photo = poiResult.results[i].photos?.get(0)?.photo_reference ?: ""
                            val lat = poiResult.results[i].geometry.location.lat
                            val lng = poiResult.results[i].geometry.location.lng
                            val poi = Poi(0, name.toString(),street, photo, lat, lng)
                            poiList.add(poi)

                            newData.value = poiList


                        }

                    }
                }
            }


        })
        return newData


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

