package org.d3if3034.assessment1_3034.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if3034.assessment1_3034.databinding.ListItemBinding
import org.d3if3034.assessment1_3034.model.Jenis

//class MainAdapter(private val data: List<Jenis>) :
////        RecyclerView.Adapter<MainAdapter.ViewHolder>() {
////
////    class ViewHolder(
////        private val binding: ListItemBinding
////    ) : RecyclerView.ViewHolder(binding.root) {
////
////        fun bind(jenis: Jenis) = with(binding) {
////            jenisBbmTextView.text = jenis.jenisBbm
////            hargaSatuanTextView.text = jenis.hargaSatuan.toString()
////            imageResId.setImageResource(jenis.imageResId)
////        }
////    }
////
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
////        val inflater = LayoutInflater.from(parent.context)
////        val binding = ListItemBinding.inflate(inflater, parent, false)
////        return ViewHolder(binding)
////    }
////
////    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
////        holder.bind(data[position])
////    }
////
////    override fun getItemCount(): Int {
////        return data.size
////    }
//}