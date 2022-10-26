package com.samad_talukder.androidsqlitecrudexample

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.samad_talukder.androidsqlitecrudexample.adapter.ExpenseItemAdapter
import com.samad_talukder.androidsqlitecrudexample.databinding.FragmentExpenseBinding
import com.samad_talukder.androidsqlitecrudexample.db.ExpenseDBHandler
import com.samad_talukder.androidsqlitecrudexample.db.model.ExpenseModel


class ExpenseFragment : Fragment() {

    private var _binding: FragmentExpenseBinding? = null
    private val binding get() = _binding!!
    private var expenseDBHandler: ExpenseDBHandler? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentExpenseBinding.inflate(inflater, container, false)

        initExpenseDB()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataIntoRecyclerView()

        binding.fabAddExpense.setOnClickListener {
            addExpenseDialog()
            //navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    private fun getExpenseList(): ArrayList<ExpenseModel>? {
        return expenseDBHandler?.viewExpense()
    }

    private fun setDataIntoRecyclerView() {
        if (getExpenseList()!!.size > 0) {
            binding.rvExpense.visibility = View.VISIBLE
            binding.tvNoDataFound.visibility = View.GONE

            val adapter = getExpenseList()?.let { expense ->
                ExpenseItemAdapter(
                    expense,
                    onItemClick = { updateDialog(it) },
                    onDeleteItemClick = { deleteItem(it) }
                )
            }

            binding.rvExpense.layoutManager = LinearLayoutManager(context)
            binding.rvExpense.adapter = adapter

        } else {
            binding.rvExpense.visibility = View.GONE
            binding.tvNoDataFound.visibility = View.VISIBLE

        }
    }

    private fun addExpenseDialog() {

        val dialog = Dialog(requireContext(), R.style.Theme_Dialog)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_update)

        val etName: EditText = dialog.findViewById(R.id.et_Name)
        val etCategory: EditText = dialog.findViewById(R.id.et_category)
        val etAmount: EditText = dialog.findViewById(R.id.et_amount)

        val tvUpdate: TextView = dialog.findViewById(R.id.tvUpdate)
        val tvCancel: TextView = dialog.findViewById(R.id.tvCancel)

        tvUpdate.text = "Add"

        tvUpdate.setOnClickListener {

            val name = etName.text.toString().trim()
            val category = etCategory.text.toString().trim()
            val amount = etAmount.text.toString().trim()

            val status = expenseDBHandler!!.addExpense(ExpenseModel(0, name, amount.toInt(), "", category))

            if (status > -1) {

                Toast.makeText(requireContext(), "Record added successfully.", Toast.LENGTH_LONG).show()

                setDataIntoRecyclerView()
            }

            dialog.dismiss()

        }

        tvCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun updateDialog(expense: ExpenseModel) {
        val dialog = Dialog(requireContext(), R.style.Theme_Dialog)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_update)

        val etName: EditText = dialog.findViewById(R.id.et_Name)
        val etCategory: EditText = dialog.findViewById(R.id.et_category)
        val etAmount: EditText = dialog.findViewById(R.id.et_amount)

        val tvUpdate: TextView = dialog.findViewById(R.id.tvUpdate)
        val tvCancel: TextView = dialog.findViewById(R.id.tvCancel)

        etName.setText(expense.name)
        etCategory.setText(expense.category)
        etAmount.setText(expense.amount.toString())

        tvUpdate.setOnClickListener {

            val name = etName.text.toString().trim()
            val category = etCategory.text.toString().trim()
            val amount = etAmount.text.toString().trim()

            val status = expenseDBHandler!!.updateExpense(ExpenseModel(expense.id, name, amount.toInt(), "", category))

            if (status > -1) {
                Toast.makeText(
                    requireContext(),
                    "Record updated successfully.",
                    Toast.LENGTH_LONG
                ).show()

                setDataIntoRecyclerView()
            }

            dialog.dismiss()

        }

        tvCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun deleteItem(expense: ExpenseModel) {
        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setTitle("Delete")
        alertBuilder.setMessage("Are you sure you wants to delete ${expense.name}.")
        alertBuilder.setIcon(android.R.drawable.ic_dialog_alert)

        alertBuilder.setPositiveButton("Yes") { dialogInterface, which ->

            val status = expenseDBHandler!!.deleteExpense(ExpenseModel(expense.id, "", 0, "", ""))

            if (status > -1) {
                Toast.makeText(
                    requireContext(),
                    "Record deleted successfully.",
                    Toast.LENGTH_LONG
                ).show()

                setDataIntoRecyclerView()
            }

            dialogInterface.dismiss()
        }

        alertBuilder.setNegativeButton("No") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }

        val alertDialog: AlertDialog = alertBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    private fun navigateToEditActivity(expenseModel: ExpenseModel) {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }


    private fun initExpenseDB() {
        expenseDBHandler = ExpenseDBHandler(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}