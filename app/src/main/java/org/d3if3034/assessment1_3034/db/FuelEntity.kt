package org.d3if3034.assessment1_3034.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fuel")
class FuelEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var jumlahHarga: Float,
    var jenisBbm: String
)