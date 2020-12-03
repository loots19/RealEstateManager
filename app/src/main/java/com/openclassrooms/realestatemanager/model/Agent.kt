package com.openclassrooms.realestatemanager.model

import java.util.*


data class Agent(
        var id : Int? = 0,
        var name: String = "",
        var email: String = ""){



    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is Agent) return false
        return this.name == other.name
    }

    override fun hashCode() = Objects.hash(id)

}





//var list : list<Property> = listOf()





