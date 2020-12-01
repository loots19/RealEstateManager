package com.openclassrooms.realestatemanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.openclassrooms.realestatemanager.database.dao.FakePropertyApi
import com.openclassrooms.realestatemanager.database.dao.PropertyDao
import com.openclassrooms.realestatemanager.model.Property

@Database(entities = [Property::class], version = 1, exportSchema = false)


abstract class AppDatabase : RoomDatabase() {

    abstract fun propertyDao(): PropertyDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "MyDatabase")
                        .addCallback(FakePropertyApi.prepopulateDatabase())
                        .allowMainThreadQueries()
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}

