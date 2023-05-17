package org.d3if3034.assessment1_3034.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3034.assessment1_3034.databinding.ItemHistoryBinding
import org.d3if3034.assessment1_3034.db.FuelEntity
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter :
    ListAdapter<FuelEntity, HistoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<FuelEntity>() {
                override fun areItemsTheSame(
                    oldData: FuelEntity, newData: FuelEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: FuelEntity, newData: FuelEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: FuelEntity) = with(binding) {
            val  = item.h()
            kategoriTextView.text = hasilBmi.kategori.toString().substring(0, 1) val colorRes = when(hasilBmi.kategori) {
            KategoriBmi.KURUS -> R.color.kurus KategoriBmi.IDEAL -> R.color.ideal else -> R.color.gemuk
        }
    }
    }