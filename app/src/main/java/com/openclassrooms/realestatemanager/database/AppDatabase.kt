package com.openclassrooms.realestatemanager.database

import android.content.Context
import androidx.room.*
import com.openclassrooms.realestatemanager.database.converters.Converters
import com.openclassrooms.realestatemanager.database.dao.FakePropertyApi
import com.openclassrooms.realestatemanager.database.dao.PhotoDao
import com.openclassrooms.realestatemanager.database.dao.PropertyDao
import com.openclassrooms.realestatemanager.model.Photo
import com.openclassrooms.realestatemanager.model.Poi
import com.openclassrooms.realestatemanager.model.Property

@Database(entities = [Property::class,Photo::class, Poi::class], version = 1, exportSchema = false)
//@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun propertyDao(): PropertyDao
    abstract fun photoDao(): PhotoDao



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

