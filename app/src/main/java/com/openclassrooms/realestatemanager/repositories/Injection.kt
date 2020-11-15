package com.openclassrooms.realestatemanager.repositories

import android.content.Context

class Injection {

    companion object {

        private fun providesAgentRepository(context: Context): AgentRepository {
            return AgentRepository()
        }


        fun providesViewModelFactory(context: Context): ViewModelFactory {

            val agentRepository = providesAgentRepository(context)

            return ViewModelFactory(agentRepository)
        }
    }
}