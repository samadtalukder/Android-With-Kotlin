package com.samad_talukder.androidsqlitecrudexample

import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.samad_talukder.androidsqlitecrudexample.databinding.ActivityAddOrEditBinding
import com.samad_talukder.androidsqlitecrudexample.db.FavouriteDBHelper
import com.samad_talukder.androidsqlitecrudexample.db.datasource.FavouriteLocalDataSourceImpl
import com.samad_talukder.androidsqlitecrudexample.db.model.FavouriteEntity

class AddOrEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddOrEditBinding
    private var favDB: FavouriteDBHelper? = null
    private var sqLiteOpenHelper: SQLiteOpenHelper? = null
    private var favouriteLocalDataSourceImpl: FavouriteLocalDataSourceImpl? = null

    var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOrEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDB()

        initOperations()

    }


    private fun initDB() {
        favDB = FavouriteDBHelper(this)
        favouriteLocalDataSourceImpl = FavouriteLocalDataSourceImpl(favDB!!)

        if (intent != null && intent.getStringExtra("operation") == "Edit") {
            isEditMode = true

            val tasks: FavouriteEntity? =
                favouriteLocalDataSourceImpl!!.getFavouriteItem(intent.getIntExtra("Id", 0))

            binding.inputName.setText(tasks?.title)
            binding.inputDesc.setText(tasks?.description)

        }
    }

    private fun initOperations() {
        binding.btnSave.setOnClickListener {

            if (!isEditMode) {

                val name = binding.inputName.text.toString().trim()
                val desc = binding.inputDesc.text.toString().trim()

                val tasks = FavouriteEntity(name, desc, System.currentTimeMillis().toString())

                val check: Boolean? = favouriteLocalDataSourceImpl?.saveFavouriteItem(tasks)

                if (check == true) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                }

            } else {

                val name = binding.inputName.text.toString().trim()
                val desc = binding.inputDesc.text.toString().trim()
                val tasks = FavouriteEntity(name, desc, System.currentTimeMillis().toString())

                val check: Boolean? = favouriteLocalDataSourceImpl?.updateFavouriteItem(tasks)

                if (check == true) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}