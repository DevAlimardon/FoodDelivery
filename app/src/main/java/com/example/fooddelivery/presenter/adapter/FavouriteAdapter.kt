package com.example.fooddelivery.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ItemProductBinding
import com.example.fooddelivery.model.FoodData
import com.example.fooddelivery.utils.scope

class FavouriteAdapter : ListAdapter<FoodData, FavouriteAdapter.ViewHolder>(DiffItem) {
    private var listenerItem: ((FoodData) -> Unit)? = null

    object DiffItem: DiffUtil.ItemCallback<FoodData>(){
        override fun areItemsTheSame(oldItem: FoodData, newItem: FoodData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FoodData, newItem: FoodData): Boolean =
            oldItem.imageURL == newItem.imageURL ||
                    oldItem.isFavourite == newItem.isFavourite ||
                    oldItem.cost == newItem.cost ||
                    oldItem.name == newItem.name ||
                    oldItem.type == newItem.type
    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding by viewBinding(ItemProductBinding::bind)

//        init {
//            binding.container.setOnClickListener {
//                listenerItem?.invoke(getItem(absoluteAdapterPosition))
//            }
//        }

        fun bind() = binding.scope {
            Glide.with(ivProductImage.context)
                .load(getItem(absoluteAdapterPosition).imageURL)
                .into(ivProductImage)
            tvTitle.text = getItem(absoluteAdapterPosition).name
            tvCost.text = getItem(absoluteAdapterPosition).cost.toString()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
    fun setProductItemListener(block: (FoodData) -> Unit){
        listenerItem = block
    }
}
