package org.d3if3034.assessment1_3034.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3034.assessment1_3034.R
import org.d3if3034.assessment1_3034.databinding.FragmentMainBinding
import org.d3if3034.assessment1_3034.model.JumlahLiter

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnHitung.setOnClickListener {
            Hitung()
        }
        binding.btnShare.setOnClickListener {
            shareData()
        }

        viewModel.getJumlahLiter().observe(requireActivity(), ::showResult)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_mainFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun Hitung() {
        val jumlahHarga = binding.jumlahHarga.text.toString()
        if(TextUtils.isEmpty(jumlahHarga)) {
            Toast.makeText(context, R.string.jumlahHargaInvalid, Toast.LENGTH_LONG).show()
            return
        }

        val jumlahHargaFloat = jumlahHarga.toFloat()

        val jenisBbm = binding.spinner.selectedItem.toString()
        if(TextUtils.isEmpty(jenisBbm)) {
            Toast.makeText(context, R.string.jenisBbmInvalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.Hitung(jumlahHargaFloat, jenisBbm)
    }

    private fun shareData() {
        val message = getString(R.string.share_template,
            binding.jumlahHarga.text,
            binding.spinner.selectedItem,
            binding.hargaSatuLiter.text,
            binding.jumlahLiter.text
        )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

    private fun showResult(result: JumlahLiter?) {
        if (result == null) return
        binding.jumlahLiter.text = "Jumlah Liter: " + result.jumlahLiter
        binding.hargaSatuLiter.text = "Harga Satu Liter: Rp. " + result.hargaSatuLiter
        binding.btnShare.visibility = View.VISIBLE
    }
}