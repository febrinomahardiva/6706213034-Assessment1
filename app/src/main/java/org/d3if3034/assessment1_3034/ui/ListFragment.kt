package org.d3if3034.assessment1_3034.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import org.d3if3034.assessment1_3034.Adapter.RecyclerViewAdapter
import org.d3if3034.assessment1_3034.databinding.FragmentInfoBensinBinding
import org.d3if3034.assessment1_3034.db.FuelDb
import org.d3if3034.assessment1_3034.network.FuelApi
import org.d3if3034.assessment1_3034.ui.hitung.MainViewModel
import org.d3if3034.assessment1_3034.ui.hitung.MainViewModelFactory

class ListFragment : Fragment() {
    private var _binding: FragmentInfoBensinBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private val viewModel: MainViewModel by lazy {
        val db = FuelDb.getInstance(requireContext())
        val factory = MainViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBensinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchData()


        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }
    }

    private fun updateProgress(status: FuelApi.ApiStatus) {
        when (status) {
            FuelApi.ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            FuelApi.ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            FuelApi.ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerViewAdapter
        }
    }

    private fun fetchData() {
        lifecycleScope.launchWhenCreated {
            try {
                val response = FuelApi.service.getFuel()
                recyclerViewAdapter.submitList(response)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
