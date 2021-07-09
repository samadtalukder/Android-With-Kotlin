package com.samad_talukder.androidviewbindinginrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.samad_talukder.androidviewbindinginrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // initialize binding
    private lateinit var mainBinding: ActivityMainBinding
    var data = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        setContentView(mainBinding.root)

        val listItem = ArrayList<IncDecModel>()

        val adapter = RvIncDecAdapter(this)

        mainBinding.buttonIncrement.setOnClickListener {
            data++
            mainBinding.textView.text = data.toString()

            listItem.add(IncDecModel(data))
            adapter.setItemInList(listItem)

        }

        mainBinding.buttonDecrement.setOnClickListener {
            if (data > 0) {
                data--
                mainBinding.textView.text = data.toString()

                listItem.removeAt(IncDecModel(data).number)
                adapter.setItemInList(listItem)

            }
        }

        val _layoutManager = LinearLayoutManager(this)

        mainBinding.rvShowData.layoutManager = _layoutManager
        mainBinding.rvShowData.adapter = adapter

    }
}


