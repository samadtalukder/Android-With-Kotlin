package com.samad_talukder.androidfragmentcommunicationwithviewmodels

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.layout_top, InputFragment(), "InputFragment").commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.layout_bottom, OutputFragment(), "OutputFragment").commit()

        val setViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setViewModel.number.observe(this, {
            it?.let {
                tv_show_input.text = "Your Status: $it"
            }
        })
    }
}