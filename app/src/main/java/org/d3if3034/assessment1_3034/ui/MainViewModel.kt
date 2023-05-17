package org.d3if3034.assessment1_3034.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3034.assessment1_3034.model.HitungLiter
import org.d3if3034.assessment1_3034.model.JumlahHarga
import org.d3if3034.assessment1_3034.model.JumlahLiter

class MainViewModel : ViewModel() {
    private val JumlahLiter = MutableLiveData<JumlahLiter?>()

    fun Hitung(jumlahHarga: Float, jenisBbm: String) {
        val dataHitung = JumlahHarga(
            jumlahHarga = jumlahHarga,
            jenisBbm = jenisBbm
        )
        JumlahLiter.value = dataHitung.HitungLiter()
    }

    fun getJumlahLiter(): LiveData<JumlahLiter?> = JumlahLiter
}