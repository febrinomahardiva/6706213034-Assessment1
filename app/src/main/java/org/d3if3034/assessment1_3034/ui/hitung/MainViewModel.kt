package org.d3if3034.assessment1_3034.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3034.assessment1_3034.db.FuelDao
import org.d3if3034.assessment1_3034.db.FuelDb
import org.d3if3034.assessment1_3034.db.FuelEntity
import org.d3if3034.assessment1_3034.model.HitungLiter
import org.d3if3034.assessment1_3034.model.JumlahHarga
import org.d3if3034.assessment1_3034.model.JumlahLiter

class MainViewModel(private val db: FuelDao) : ViewModel() {
    private val JumlahLiter = MutableLiveData<JumlahLiter?>()

    fun Hitung(jumlahHarga: Float, jenisBbm: String) {
        val dataFuel = FuelEntity(
            jumlahHarga = jumlahHarga,
            jenisBbm = jenisBbm
        )
        JumlahLiter.value = dataFuel.HitungLiter()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataFuel)
            }
        }
    }

    fun getJumlahLiter(): LiveData<JumlahLiter?> = JumlahLiter
}