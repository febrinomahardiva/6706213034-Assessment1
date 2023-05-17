package org.d3if3034.assessment1_3034.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FuelEntity::class], version = 1, exportSchema = false)
abstract class FuelDb : RoomDatabase() {

    abstract val dao: FuelDao

    companion object {

        @Volatile
        private var INSTANCE: FuelDb? = null

        fun getInstance(context: Context): FuelDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FuelDb::class.java,
                        "fuel.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}