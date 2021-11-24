package com.example.fooddelivery.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.LocationItemBinding
import com.example.fooddelivery.model.LocationData
import com.example.fooddelivery.utils.scope

class LocationAdapter : ListAdapter<LocationData, LocationAdapter.ViewHolder>(DiffItem) {
    private var listenerItem: ((LocationData) -> Unit)? = null

    object DiffItem: DiffUtil.ItemCallback<LocationData>(){
        override fun areItemsTheSame(oldItem: LocationData, newItem: LocationData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LocationData, newItem: LocationData): Boolean =
                    oldItem.name == newItem.name||
                    oldItem.description == newItem.description||
                    oldItem.latitude == newItem.latitude||
                    oldItem.longitude == newItem.longitude||
                    oldItem.time == newItem.time||
                    oldItem.type == newItem.type
    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding by viewBinding(LocationItemBinding::bind)

        init {
            binding.container.setOnClickListener {
                listenerItem?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind() = binding.scope {
            tvName.text = getItem(absoluteAdapterPosition).name
            tvDescription.text = getItem(absoluteAdapterPosition).description
            tvTime.text = getItem(absoluteAdapterPosition).time
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.location_item,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
    fun setLocationItemListener(block: (LocationData) -> Unit){
        listenerItem = block
    }
}
