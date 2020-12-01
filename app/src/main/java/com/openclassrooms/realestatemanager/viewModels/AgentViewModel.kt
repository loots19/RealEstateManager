package com.openclassrooms.realestatemanager.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.openclassrooms.realestatemanager.database.repositories.AgentRepository
import com.openclassrooms.realestatemanager.model.Agent


class AgentViewModel(private val agentRepository: AgentRepository): ViewModel() {

    fun getAgent(): LiveData<Agent>{
        return agentRepository.getWorkmateName()

    }

}







