package org.d3if3034.assessment1_3034.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3034.assessment1_3034.R
import org.d3if3034.assessment1_3034.databinding.ListItemBinding
import org.d3if3034.assessment1_3034.model.Jenis
import org.d3if3034.assessment1_3034.network.FuelApi

class RecyclerViewAdapter :
    ListAdapter<Jenis, RecyclerViewAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = getItem(position)
        holder.bind(currentData)
    }

    inner class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Jenis) {
            binding.jenisBbmTextView.text = data.jenisBbm
            binding.hargaSatuanTextView.text = data.hargaSatuan

            // Load image using Glide
            Glide.with(binding.imageResId.context)
                .load(FuelApi.getFuelUrl(data.imageResId))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(binding.imageResId)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Jenis>() {
        override fun areItemsTheSame(oldItem: Jenis, newItem: Jenis): Boolean {
            return oldItem.jenisBbm == newItem.jenisBbm
        }

        override fun areContentsTheSame(oldItem: Jenis, newItem: Jenis): Boolean {
            return oldItem == newItem
        }
    }
}
