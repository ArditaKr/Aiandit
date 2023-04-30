package com.arditakrasniqi.aiandit.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arditakrasniqi.aiandit.data.model.requests.CreateVehicleModel
import com.arditakrasniqi.aiandit.databinding.FragmentHomeBinding
import com.arditakrasniqi.aiandit.presentation.adapters.VehiclesAdapter
import com.arditakrasniqi.aiandit.presentation.utils.DataState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var vehiclesAdapter: VehiclesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.getUserId().isNullOrEmpty()) {
            findNavController().navigate(HomeFragmentDirections.actionNavHomeToLoginFragment())
            return
        }
        makeApiCall()
        observeData()
        initAdapter()
        onClick()
    }

    private fun onClick() {
        binding.createVehicleBtn.setOnClickListener {
            val userId = viewModel.getUserId()
            if (userId != null) {
                val createVehicleModelBody = CreateVehicleModel(vin = "Test Ardita")
                viewModel.createVehicle(userId, createVehicleModelBody)
            }
        }
    }

    private fun makeApiCall() {
        val userId = viewModel.getUserId()
        if (userId != null) {
            viewModel.getVehiclesFromAPI(userId)
        }
    }

    private fun observeData() {
        viewModel.vehicles.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {

                }
                is DataState.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
                is DataState.Success -> {
                    val jsonResponse = it.data?.body()
                    vehiclesAdapter.differ.submitList(jsonResponse)
                }
            }
        }

        viewModel.createVehicles.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    Log.d("TAG", "observeData: loading")
                }
                is DataState.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
                is DataState.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Vehicle Created Successfully!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


    private fun initAdapter() {
        vehiclesAdapter = VehiclesAdapter()
        binding.vehiclesRV.apply {
            adapter = vehiclesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}