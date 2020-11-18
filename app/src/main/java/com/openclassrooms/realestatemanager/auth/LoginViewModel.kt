package com.openclassrooms.realestatemanager.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.openclassrooms.realestatemanager.repositories.AgentRepository

class LoginViewModel(private val agentRepository: AgentRepository) : ViewModel() {

    private var userLiveData: MutableLiveData<FirebaseUser>? = agentRepository.getUserLiveData()


    // -----------------------------------------
    // ----- Create a workmate in fireBase -----
    // -----------------------------------------
    fun createWorkmate(name: String, mail: String) {
        agentRepository.createAgent(name, mail)
    }

    // ------------------------------------------------------------------
    // ----- register Agent with email and password in fireBase -----
    // ------------------------------------------------------------------
    fun register(email: String, password: String) {
        agentRepository.register(email, password)
    }

    // ------------------------------------------------------------------
    // ----- register Agent with email and password in fireBase -----
    // ------------------------------------------------------------------
    fun logIn(email: String, password: String) {
        agentRepository.login(email, password)
    }

    fun logout() {
        agentRepository.logout()
    }

    fun getUserLiveData(): MutableLiveData<FirebaseUser>? {
        if (userLiveData != null) {
            this.userLiveData
        }
        return userLiveData
    }

   
}