package com.arditakrasniqi.aiandit.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arditakrasniqi.aiandit.data.model.responses.Vehicle
import com.arditakrasniqi.aiandit.databinding.VehiclesItemBinding


class VehiclesAdapter : RecyclerView.Adapter<VehiclesAdapter.VehiclesViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<Vehicle>() {
        override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem.vehicleId == newItem.vehicleId
        }

        override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesViewHolder {
        val binding =
            VehiclesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehiclesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun onBindViewHolder(holder: VehiclesViewHolder, position: Int) {
        val vehicles = differ.currentList[position]
        holder.bind(vehicles)
    }

    inner class VehiclesViewHolder(
        private val binding: VehiclesItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vehiclesModel: Vehicle) {

            binding.vehiclesId.text = vehiclesModel.vehicleId

        }
    }
}