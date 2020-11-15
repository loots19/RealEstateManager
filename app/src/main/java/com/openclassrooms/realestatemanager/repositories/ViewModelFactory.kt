package com.openclassrooms.realestatemanager.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.openclassrooms.realestatemanager.auth.LoginViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (
        private val agentRepository: AgentRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java)->
                return modelClass.cast(LoginViewModel(agentRepository))!!

        }
        throw IllegalArgumentException("UnKnown ViewModel class")
    }

}