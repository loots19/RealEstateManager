package com.openclassrooms.realestatemanager.retrofit

import com.openclassrooms.realestatemanager.model.PoiResult
import com.openclassrooms.realestatemanager.utils.UtilsKotlin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET("api/place/nearbysearch/json?key=" + UtilsKotlin.API_KEY)
    fun getPoiFromProperty(@Query("location") location: String,
                           @Query("radius") radius: Int): Call<PoiResult>

}
