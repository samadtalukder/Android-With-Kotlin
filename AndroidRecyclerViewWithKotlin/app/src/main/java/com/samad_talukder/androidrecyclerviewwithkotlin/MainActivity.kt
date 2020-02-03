package com.samad_talukder.androidrecyclerviewwithkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize arraylist
        val androidVersionArrayList = ArrayList<AndroidVersion>()

        androidVersionArrayList.add(AndroidVersion("Android Cupcake", 1.5))
        androidVersionArrayList.add(AndroidVersion("Android Donut", 1.6))
        androidVersionArrayList.add(AndroidVersion("Android Eclair", 2.0))
        androidVersionArrayList.add(AndroidVersion("Android Froyo", 2.2))
        androidVersionArrayList.add(AndroidVersion("Android Gingerbread", 2.3))
        androidVersionArrayList.add(AndroidVersion("Android Honeycomb", 3.0))
        androidVersionArrayList.add(AndroidVersion("Android Ice Cream Sandwich", 4.0))
        androidVersionArrayList.add(AndroidVersion("Android Jelly Bean", 4.1))
        androidVersionArrayList.add(AndroidVersion("Android KitKat", 4.4))
        androidVersionArrayList.add(AndroidVersion("Android Lollipop", 5.0))
        androidVersionArrayList.add(AndroidVersion("Android Marshmallow", 6.0))
        androidVersionArrayList.add(AndroidVersion("Android Nougat", 7.0))
        androidVersionArrayList.add(AndroidVersion("Android Oreo", 8.0))
        androidVersionArrayList.add(AndroidVersion("Android Pie", 9.0))

        // initialize recyclerview
        val rvData = findViewById<RecyclerView>(R.id.recycler_view)


        // set default vertical layout manager
        rvData.layoutManager = LinearLayoutManager(this)

        // set layout manager in recyclerview
        // rvData.layoutManager = LinearLayoutManager(this, VERTICAL, false)

        // set adapter
        rvData.adapter = AndroidVersionAdapter(androidVersionArrayList)

    }
}
