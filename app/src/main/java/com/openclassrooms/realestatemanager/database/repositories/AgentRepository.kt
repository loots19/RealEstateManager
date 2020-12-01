package com.openclassrooms.realestatemanager.database.repositories

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.firebase.ui.auth.AuthUI.getApplicationContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.openclassrooms.realestatemanager.model.Agent
import java.util.*
import java.util.Objects.requireNonNull

class AgentRepository (){


    private var userLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()

    // ----- getter -----
    fun getUserLiveData(): MutableLiveData<FirebaseUser> {
        return userLiveData
    }


    // --------------------------------
    // ----- COLLECTION REFERENCE -----
    // --------------------------------
    private fun getAgentCollection(): CollectionReference {
        return FirebaseFirestore.getInstance().collection("agent")
    }

    // ------------------
    // ----- CREATE -----
    // ------------------
    fun createAgent(name: String, mail: String) {
        val uid: String = FirebaseAuth.getInstance().uid.toString()
        val agentToCreate = Agent(null,name, mail)
        getAgentCollection().document(uid).set(agentToCreate)
    }


    // ------------------------------------------------------------------
    // ----- register Agent with email and password in fireBase -----
    // ------------------------------------------------------------------
    @SuppressLint("RestrictedApi", "NewApi")
    fun register(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        userLiveData.postValue(FirebaseAuth.getInstance().currentUser)
                    } else {
                        Toast.makeText(getApplicationContext().applicationContext, "Registration Failure: " + Objects.requireNonNull(it.exception), Toast.LENGTH_SHORT).show()
                    }
                }
    }

    // ------------------------------------------------------------------
    // ----- Login Agent with email and password in fireBase --------
    // ------------------------------------------------------------------
    @SuppressLint("RestrictedApi", "NewApi")
    fun login(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        userLiveData.postValue(FirebaseAuth.getInstance().currentUser)
                    } else {
                        Toast.makeText(getApplicationContext().applicationContext, "Registration Failure: " + Objects.requireNonNull(it.exception), Toast.LENGTH_SHORT).show()
                    }
                }
    }

    // ---------------------------
    // ----- Logout Agent --------
    // ---------------------------
    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }

    fun getWorkmateName(): MutableLiveData<Agent> {
        val mutableLiveData = MutableLiveData<Agent>()
        val uid = FirebaseAuth.getInstance().uid
        getAgentCollection().document(uid.toString()).get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val agent = documentSnapshot.toObject(Agent::class.java)
                mutableLiveData.value = agent
            }
        }
        return mutableLiveData
    }






}







