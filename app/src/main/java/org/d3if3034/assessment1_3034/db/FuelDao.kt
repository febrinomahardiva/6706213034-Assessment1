package org.d3if3034.assessment1_3034.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FuelDao {

    @Insert
    fun insert(fuel: FuelEntity)

    @Query("SELECT * FROM fuel ORDER BY id DESC")
    fun getLastFuel(): LiveData<List<FuelEntity>>

    @Query("DELETE FROM fuel")
    fun clearData()
}