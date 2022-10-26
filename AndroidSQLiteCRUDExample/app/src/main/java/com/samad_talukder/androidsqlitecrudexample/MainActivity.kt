package com.samad_talukder.androidsqlitecrudexample

import android.content.Intent
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.samad_talukder.androidsqlitecrudexample.adapter.FavouriteItemAdapter
import com.samad_talukder.androidsqlitecrudexample.databinding.ActivityMainBinding
import com.samad_talukder.androidsqlitecrudexample.db.ExpenseDBHandler
import com.samad_talukder.androidsqlitecrudexample.db.FavouriteDBHelper
import com.samad_talukder.androidsqlitecrudexample.db.datasource.FavouriteLocalDataSourceImpl
import com.samad_talukder.androidsqlitecrudexample.db.model.FavouriteEntity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var favDB: FavouriteDBHelper? = null

    private var sqLiteOpenHelper: SQLiteOpenHelper? = null
    var favouriteList: List<FavouriteEntity> = ArrayList()
    private var favouriteLocalDataSourceImpl: FavouriteLocalDataSourceImpl? = null
    var linearLayoutManager: LinearLayoutManager? = null
    var favItemAdapter: FavouriteItemAdapter? = null
    private var expenseDBHandler: ExpenseDBHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("Activity_State", "onCreate")

        //initDB()
        initExpenseDB()
        initViews()


    }

    private fun initExpenseDB() {
        expenseDBHandler = ExpenseDBHandler(this)
    }

    private fun initViews() {

        setSupportActionBar(binding.toolbar)


        binding.tvColor.setTextColor(Utils.randomColor())

        favItemAdapter = FavouriteItemAdapter(favouriteList, this)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        binding.rv.rvFavourite.layoutManager = linearLayoutManager
        binding.rv.rvFavourite.adapter = favItemAdapter

        binding.fab.setOnClickListener {
            val i = Intent(applicationContext, ExpenseActivity::class.java)
            startActivity(i)

            /*val i = Intent(applicationContext, AddOrEditActivity::class.java)
            i.putExtra("operation", "Add")
            startActivity(i)*/
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("Activity_State", "onStart")
    }

    override fun onResume() {
        super.onResume()
        //initDB()
        initViews()
        Log.e("Activity_State", "onResume")


    }

    override fun onPause() {
        super.onPause()
        Log.e("Activity_State", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("Activity_State", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("Activity_State", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Activity_State", "onDestroy")
    }


    private fun initDB() {
        favDB = FavouriteDBHelper(this)
        favouriteLocalDataSourceImpl = FavouriteLocalDataSourceImpl(favDB!!)
        favouriteList = favouriteLocalDataSourceImpl!!.getFavouriteList()
    }
}