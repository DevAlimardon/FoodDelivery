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
import com.example.fooddelivery.databinding.ItemListBinding
import com.example.fooddelivery.model.FoodData


class MenuAdapter : ListAdapter<FoodData, MenuAdapter.ViewHolder>(DiffItem) {
    private var increaseListener: ((Long) -> Unit)? = null
    private var decreaseListener: ((Long) -> Unit)? = null
    private var itemListener: ((FoodData) -> Unit)? = null

    object DiffItem : DiffUtil.ItemCallback<FoodData>() {
        override fun areItemsTheSame(oldItem: FoodData, newItem: FoodData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FoodData, newItem: FoodData): Boolean =
            oldItem == newItem
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding by viewBinding(ItemListBinding::bind)

        init {
            binding.container.setOnClickListener {
                itemListener?.invoke(getItem(absoluteAdapterPosition))
            }
            binding.tvBasket.setOnClickListener {
                binding.tvBasket.visibility = View.INVISIBLE
                binding.llItems.visibility = View.VISIBLE
                increaseListener?.invoke(getItem(absoluteAdapterPosition).id)
            }

            binding.addItem.setOnClickListener {
                increaseListener?.invoke(getItem(absoluteAdapterPosition).id)
            }

            binding.deleteItem.setOnClickListener {
                decreaseListener?.invoke(getItem(absoluteAdapterPosition).id)
            }
        }

        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                Glide.with(binding.ivItemImage.context)
                    .load(imageURL)
                    .into(binding.ivItemImage)

                if (getItem(absoluteAdapterPosition).count == 0L) {
                    binding.tvBasket.visibility = View.VISIBLE
                    binding.llItems.visibility = View.INVISIBLE
                } else {
                    binding.tvBasket.visibility = View.INVISIBLE
                    binding.llItems.visibility = View.VISIBLE
                }
                binding.tvItemName.text = name
                binding.tvItemCost.text = cost.toString()
                binding.countItem.text = count.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    fun setItemClickListener(block: (FoodData) -> Unit) {
        itemListener = block
    }

    fun setDecreaseItemClickListener(block: (Long) -> Unit) {
        decreaseListener = block
    }

    fun setIncreaseItemClickListener(block: (Long) -> Unit) {
        increaseListener = block
    }
}
