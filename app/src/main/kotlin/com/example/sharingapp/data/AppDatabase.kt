package com.example.sharingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sharingapp.data.items.ItemDao
import com.example.sharingapp.data.contacts.ContactDao
import com.example.sharingapp.model.Item
import com.example.sharingapp.model.Contact

/*
 * This class implements the Singleton pattern
 */

@Database(entities = [Item::class, Contact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun contactDao() : ContactDao

    companion object {
        // @Volatile ensures that the value of INSTANCE is always up-to-date and the same for all execution threads.
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase{
            // if not null, return the existing instance
            // if null, create the database in a synchronized block to ensure thread-safety
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" // the file name that will be stored in the databases/ dir (in internal storage)
                )
                    .fallbackToDestructiveMigration(false) // PRE-production - MUST BE CHANGED for proper production standards
                    .build()

                // INSTANCE no longer null, so next time, we can simply return
                INSTANCE = instance

                // return this instance
                instance
            } // end return statement
        } // end fun getDatabase


    }
}