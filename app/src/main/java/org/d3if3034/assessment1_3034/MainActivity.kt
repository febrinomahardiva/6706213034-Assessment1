package org.d3if3034.assessment1_3034

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if3034.assessment1_3034.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHitung.setOnClickListener {
            Hitung()
        }
    }

    fun Hitung() {
        val jumlahHarga = binding.jumlahHarga.text.toString()
        if(TextUtils.isEmpty(jumlahHarga)) {
            Toast.makeText(this, "Jumlah Harga Tidak Boleh Kosong!", Toast.LENGTH_LONG).show()
            return
        }

        val jenisBbm = binding.spinner.selectedItem.toString()
        if(TextUtils.isEmpty(jenisBbm)) {
            Toast.makeText(this, "Pilih Jenis BBM!", Toast.LENGTH_LONG).show()
            return
        }

        var total = 0f
        if(jenisBbm.equals("Pertalite", ignoreCase = true)) {
            val harga = 10000
            total = jumlahHarga.toFloat() / harga
            binding.hargaSatuLiter.text = "Harga Satu Liter: Rp. 10.000"
        } else if(jenisBbm.equals("Pertamax", ignoreCase = true)) {
            val harga = 13550
            total = jumlahHarga.toFloat() / harga
            binding.hargaSatuLiter.text = "Harga Satu Liter: Rp. 13.550"
        } else if(jenisBbm.equals("Solar", ignoreCase = true)) {
            val harga = 15250
            total = jumlahHarga.toFloat() / harga
            binding.hargaSatuLiter.text = "Harga Satu Liter: Rp. 15.250"
        }

        if(total > 0) {
            binding.jumlahLiter.text = "Jumlah Liter: " + total.toString()
        }
    }
}