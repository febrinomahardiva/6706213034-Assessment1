package org.d3if3034.assessment1_3034.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3034.assessment1_3034.R
import org.d3if3034.assessment1_3034.databinding.ItemHistoryBinding
import org.d3if3034.assessment1_3034.db.FuelEntity
import org.d3if3034.assessment1_3034.model.HitungLiter
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

                @SuppressLint("DiffUtilEquals")
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
            val jumlahLiter = item.HitungLiter().jumlahLiter
            val totalHarga = item.jumlahHarga

            kategoriTextView.text = item.jenisBbm.toString().substring(0, 1)

            if (item.jenisBbm == "Pertalite"){
                kategoriTextView.setBackgroundResource(R.mipmap.ic_pertalite)
            }else if (item.jenisBbm == "Pertamax"){
                kategoriTextView.setBackgroundResource(R.mipmap.ic_pertamax)
            }else {
                kategoriTextView.setBackgroundResource(R.mipmap.ic_solar)
            }

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            hasilTextView.text = "Jumlah Liter: ${item.HitungLiter().jumlahLiter}"
            dataTextView.text = "Jumlah Harga: ${item.jumlahHarga} / Harga per Liter: ${item.HitungLiter().hargaSatuLiter}"


        }
    }
}