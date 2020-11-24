package com.openclassrooms.realestatemanager.repositories

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.openclassrooms.realestatemanager.model.Property

class PropertyRepository(private val property: Property) {

    // --------------------------------
    // ----- COLLECTION REFERENCE -----
    // --------------------------------
    private fun getPropertyCollection(): CollectionReference {
        return FirebaseFirestore.getInstance().collection("property")
    }

    // ------------------
    // ----- CREATE -----
    // ------------------


}