package com.samad_talukder.androidsqlitecrudexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samad_talukder.androidsqlitecrudexample.databinding.ItemExpenseBinding
import com.samad_talukder.androidsqlitecrudexample.db.model.ExpenseModel

class ExpenseItemAdapter(
    private val expenseList: ArrayList<ExpenseModel>,
    val onItemClick: ((ExpenseModel) -> Unit)?,
    val onDeleteItemClick: ((ExpenseModel) -> Unit)?,
) :
    RecyclerView.Adapter<ExpenseItemAdapter.ExpenseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bindData(expenseList[position], position)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    inner class ExpenseViewHolder(private val view: ItemExpenseBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bindData(fav: ExpenseModel, position: Int) {
            view.tvCatName.text = fav.category
            view.tvAmount.text = fav.amount.toString()
            view.tvDate.text = fav.name

            view.llCatMain.setOnClickListener {
                val expenseModel:ExpenseModel = expenseList[position]

                onItemClick?.let { ex-> ex(expenseModel) }
            }

            view.ivDelete.setOnClickListener {
                val expenseModel:ExpenseModel = expenseList[position]

                onDeleteItemClick?.let { ex-> ex(expenseModel) }
            }

        }

    }
}