package com.openclassrooms.realestatemanager.koin

import android.accessibilityservice.GestureDescription
import android.content.Context
import androidx.constraintlayout.solver.Cache
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.openclassrooms.realestatemanager.BuildConfig
import com.openclassrooms.realestatemanager.auth.LoginViewModel
import com.openclassrooms.realestatemanager.database.AppDatabase
import com.openclassrooms.realestatemanager.database.repositories.AgentRepository
import com.openclassrooms.realestatemanager.database.repositories.PhotoRepository
import com.openclassrooms.realestatemanager.database.repositories.PropertyRepository
import com.openclassrooms.realestatemanager.retrofit.ApiRequest
import com.openclassrooms.realestatemanager.viewModels.AgentViewModel
import com.openclassrooms.realestatemanager.viewModels.PhotoViewModel
import com.openclassrooms.realestatemanager.viewModels.PropertyViewModel
import com.squareup.okhttp.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


val vMLoginModule = module {
    viewModel {
        LoginViewModel(get())
    }
}
val vMAgentModule = module {
    viewModel {
        AgentViewModel(get())
    }
}


val repositoryAgentModule = module {
    single {
        AgentRepository()
    }
}
val vMPropertyModule = module {
    viewModel {
        PropertyViewModel(get())
    }
}
val repositoryProperty = module {
    single {
        PropertyRepository(get())
    }
}
val repositoryPhoto = module {
    single {
        PhotoRepository(get())
    }
}
val vMPhotoModule = module {
    single {
        PhotoViewModel(get())
    }
}

val appModule = module {

    single { AppDatabase.getDatabase(get()) }

    single { get<AppDatabase>().propertyDao() }

    single { get<AppDatabase>().photoDao() }






}




