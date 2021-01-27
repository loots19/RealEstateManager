package com.openclassrooms.realestatemanager.koin

import com.openclassrooms.realestatemanager.auth.LoginViewModel
import com.openclassrooms.realestatemanager.database.AppDatabase
import com.openclassrooms.realestatemanager.database.repositories.AgentRepository
import com.openclassrooms.realestatemanager.database.repositories.PhotoRepository
import com.openclassrooms.realestatemanager.database.repositories.PropertyRepository
import com.openclassrooms.realestatemanager.viewModels.AgentViewModel
import com.openclassrooms.realestatemanager.viewModels.PhotoViewModel
import com.openclassrooms.realestatemanager.viewModels.PropertyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { LoginViewModel(get()) }

    viewModel { AgentViewModel(get()) }

    viewModel { PropertyViewModel(get(), get()) }

    viewModel { PhotoViewModel(get()) }

}


val repositoryModule = module {
    single { AgentRepository() }

    single { PropertyRepository(get()) }

    single { PhotoRepository(get()) }


}


val appModule = module {

    single { AppDatabase.getDatabase(get()) }

    single { get<AppDatabase>().propertyDao() }

    single { get<AppDatabase>().photoDao() }


}





