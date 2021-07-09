package com.samad_talukder.androidviewbindinginrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samad_talukder.androidviewbindinginrecyclerview.databinding.ItemDataBinding


class RvIncDecAdapter(context: Context) : RecyclerView.Adapter<IncDecViewHolder>() {
    private var itemsList: ArrayList<IncDecModel> = ArrayList()

    fun setItemInList(_items: ArrayList<IncDecModel>) {
        this.itemsList = _items
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncDecViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return IncDecViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IncDecViewHolder, position: Int) {
        val itemsList = itemsList[position]

        holder.itemDataBinding(itemsList)

    }

    override fun getItemCount(): Int {
        return itemsList.size
    }


}