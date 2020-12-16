package com.openclassrooms.realestatemanager.database.repositories

import android.app.Application
import android.util.Log
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
        //  apiRequest = RetrofitRequest.getApiRequest()


    }

    fun poiList(location: String, radius: Int): MutableLiveData<List<Poi>> {
        val newData: MutableLiveData<List<Poi>> = MutableLiveData()
        val apiRequest = RetrofitRequest.getApiRequest()

        apiRequest.getPoiFromProperty(location, radius).enqueue(object : Callback<PoiResult> {
            override fun onResponse(call: Call<PoiResult>, response: Response<PoiResult>) {
                if (response.body()?.PoiResult()?.results != null) {
                    poiList = response.body() as ArrayList<Poi>

                    val test = poiList
                    Log.e("resultRepo", "" + poiList)

                }

            }

            override fun onFailure(call: Call<PoiResult>, t: Throwable) {
                newData.value = null
                Log.e("resultRetro", "false")

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

