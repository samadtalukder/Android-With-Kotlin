package com.samad_talukder.androidviewbindinginrecyclerview

import androidx.recyclerview.widget.RecyclerView
import com.samad_talukder.androidviewbindinginrecyclerview.databinding.ItemDataBinding

class IncDecViewHolder(var itemDataBinding: ItemDataBinding) :
    RecyclerView.ViewHolder(itemDataBinding.root) {

    fun itemDataBinding(incDecModel: IncDecModel) {
        val number = incDecModel.number
        itemDataBinding.tvShowNumber.text = number.toString()
    }


}