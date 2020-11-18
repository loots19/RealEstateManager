package com.openclassrooms.realestatemanager.repositories

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.firebase.ui.auth.AuthUI.getApplicationContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.openclassrooms.realestatemanager.MainActivity
import com.openclassrooms.realestatemanager.SplashActivity
import com.openclassrooms.realestatemanager.model.Agent
import org.koin.dsl.module.applicationContext
import java.util.*

class AgentRepository {


    private var userLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()


    // ----- getter -----
    fun getUserLiveData(): MutableLiveData<FirebaseUser> {
        return userLiveData
    }



    // --------------------------------
    // ----- COLLECTION REFERENCE -----
    // --------------------------------
    private fun getWorkmatesCollection(): CollectionReference {
        return FirebaseFirestore.getInstance().collection("agent")
    }

    // ------------------
    // ----- CREATE -----
    // ------------------
    fun createAgent(name: String, mail: String) {
        val agentToCreate = Agent(name, mail)
        val uid: String? = FirebaseAuth.getInstance().uid
        if (uid != null) {
            getWorkmatesCollection().document(uid).set(agentToCreate)
        }
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
    fun logout(){
        FirebaseAuth.getInstance().signOut()
    }


}


