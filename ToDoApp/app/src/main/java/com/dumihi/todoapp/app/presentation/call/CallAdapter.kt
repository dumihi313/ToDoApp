package com.dumihi.todoapp.app.presentation.call

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dumihi.todoapp.R
import com.dumihi.todoapp.data.model.Call
import com.dumihi.todoapp.utils.inflate

class CallAdapter : RecyclerView.Adapter<CallAdapter.CallViewHolder>() {

    private val list = mutableListOf<Call>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Call>?) {
        list.clear()
        data?.let {
            list.addAll(it)
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        return CallViewHolder(parent.inflate(R.layout.item_call))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        val profile = list[position]
        holder.bindView(profile)
    }

    inner class CallViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.name)
        private val tele = view.findViewById<TextView>(R.id.tele)

        fun bindView(data: Call) {
            name.text = data.name
            tele.text = data.number
        }
    }
}