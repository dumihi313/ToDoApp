package com.dumihi.todoapp.app.presentation.buy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dumihi.todoapp.data.local.Product
import com.dumihi.todoapp.databinding.ItemProductBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.CallViewHolder>() {

    private val list = mutableListOf<Product>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Product>?) {
        list.clear()
        data?.let {
            list.addAll(it)
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        return CallViewHolder(ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        val profile = list[position]
        holder.bindView(profile)
    }

    inner class CallViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(data: Product) {
            with(binding) {
                name.text = data.name
                price.text = data.price.toString()
                quantity.text = data.quantity.toString()
            }
        }
    }
}