package com.samad_talukder.androidtextviewwithkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var defaultValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIncrement.setOnClickListener {
            _callIncrement()
        }

        btnDecrement.setOnClickListener {
            _callDecrement()
        }

    }


    private fun _callIncrement() {
        defaultValue++
        tvHello.text = "Increment: $defaultValue"
    }

    private fun _callDecrement() {

        if (defaultValue == 0) {
            tvHello.text = "0"
            return
        }
        defaultValue--
        tvHello.text = "Decrement: $defaultValue"
    }
}
