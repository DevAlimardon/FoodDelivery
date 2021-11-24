package com.example.fooddelivery.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.R

class PopularItemAdapter :
    RecyclerView.Adapter<PopularItemAdapter.ViewHolder>() {

    private var listener: ((Int) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val productName: TextView = itemView.findViewById(R.id.tvItemName)
        private val productCost: TextView = itemView.findViewById(R.id.tvItemCost)

        init {
            itemView.setOnLongClickListener {
                listener?.invoke(absoluteAdapterPosition)
                return@setOnLongClickListener true
            }
        }

        fun bind() {
            itemView.apply {
                productName.text = "Go`shtli lavash"
                productCost.text = "21 000 so`m"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = 4

    fun setListener(f: (Int) -> Unit) {
        listener = f
    }
}