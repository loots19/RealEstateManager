package com.openclassrooms.realestatemanager.retrofit

import com.openclassrooms.realestatemanager.utils.UtilsKotlin
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRequest {

    companion object {
        private val retrofit = buildRetrofit().build()
        private val mApiRequest = retrofit.create(ApiRequest::class.java)

        fun getApiRequest(): ApiRequest {
            return mApiRequest
        }

        // Setting Up the Retrofit Interface
        private fun buildRetrofit(): Retrofit.Builder {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(UtilsKotlin.baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
        }
    }
}