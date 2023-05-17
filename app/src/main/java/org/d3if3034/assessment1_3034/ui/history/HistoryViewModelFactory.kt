package org.d3if3034.assessment1_3034.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3034.assessment1_3034.db.FuelDao

class HistoryViewModelFactory(
    private val db: FuelDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

