package com.samad_talukder.androidsqlitecrudexample

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.samad_talukder.androidsqlitecrudexample.adapter.ExpenseItemAdapter
import com.samad_talukder.androidsqlitecrudexample.databinding.ActivityExpenseBinding
import com.samad_talukder.androidsqlitecrudexample.db.ExpenseDBHandler
import com.samad_talukder.androidsqlitecrudexample.db.model.ExpenseModel

class ExpenseActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityExpenseBinding
    private var expenseDBHandler: ExpenseDBHandler? = null

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_expense) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        initExpenseDB()

    }

    private fun initExpenseDB() {
        expenseDBHandler = ExpenseDBHandler(this)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_expense)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}