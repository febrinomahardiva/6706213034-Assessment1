package org.d3if3034.assessment1_3034.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3034.assessment1_3034.db.FuelDao

class HistoryViewModel(private val db: FuelDao) : ViewModel() {
    val data = db.getLastFuel()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}
