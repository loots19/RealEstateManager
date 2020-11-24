package com.openclassrooms.realestatemanager.database

import android.content.ContentValues
import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.openclassrooms.realestatemanager.database.dao.PropertyDao
import com.openclassrooms.realestatemanager.model.Agent

//@Database(entities = [Agent::class], version = 1, exportSchema = false)


abstract class AppDatabase : RoomDatabase() {


    abstract fun propertyDao(): PropertyDao


    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {

            val temp = this.INSTANCE

            if (temp != null) {
                return temp
            }
            synchronized(AppDatabase::class) {
                this.INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "MyDatabase").build()
                return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "MyDatabase").addCallback(prepopulateDatabase()).build()
            }
        }

        private fun prepopulateDatabase(): Callback {
            return object : Callback() {
                override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                    super.onCreate(db)


                }
            }
        }
    }
}