package com.openclassrooms.realestatemanager.koin

import com.openclassrooms.realestatemanager.auth.LoginViewModel
import com.openclassrooms.realestatemanager.database.AppDatabase
import com.openclassrooms.realestatemanager.database.repositories.AgentRepository
import com.openclassrooms.realestatemanager.database.repositories.PropertyRepository
import com.openclassrooms.realestatemanager.viewModels.AgentViewModel
import com.openclassrooms.realestatemanager.viewModels.PropertyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


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
val vMPropertyModule = module{
    viewModel {
        PropertyViewModel(get())
    }
}
val repositoryProperty = module {
    single {
        PropertyRepository(get())
    }
}

val appModule = module {

    single { AppDatabase.getDatabase(get()) }

    single {get<AppDatabase>().propertyDao()  }

}
