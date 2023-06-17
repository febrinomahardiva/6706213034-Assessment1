package org.d3if3034.assessment1_3034.ui.hitung

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3034.assessment1_3034.MainActivity
import org.d3if3034.assessment1_3034.db.FuelDao
import org.d3if3034.assessment1_3034.db.FuelEntity
import org.d3if3034.assessment1_3034.model.HitungLiter
import org.d3if3034.assessment1_3034.model.Jenis
import org.d3if3034.assessment1_3034.model.JumlahLiter
import org.d3if3034.assessment1_3034.network.FuelApi
import org.d3if3034.assessment1_3034.network.UpdateWorker
import java.util.concurrent.TimeUnit

class MainViewModel(private val db: FuelDao) : ViewModel() {
    private val JumlahLiter = MutableLiveData<JumlahLiter?>()

    private val data = MutableLiveData<List<Jenis>>()
    private val status = MutableLiveData<FuelApi.ApiStatus>()

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

    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(FuelApi.ApiStatus.LOADING)
            try {
                data.postValue(FuelApi.service.getFuel())
                status.postValue(FuelApi.ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.postValue(FuelApi.ApiStatus.FAILED)
            }
        }
    }

    fun getStatus(): LiveData<FuelApi.ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request )
    }
}