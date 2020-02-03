package com.samad_talukder.androidkotlinbuttononclickexample

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize views

        val tv = findViewById<TextView>(R.id.text_view)
        val btnIncrement = findViewById<TextView>(R.id.btnInc)
        val btnDecrement = findViewById<TextView>(R.id.btnDec)

        // button click count
        var count: Int = 0

        // button on click action

        btnIncrement.setOnClickListener(View.OnClickListener {
            count++

            tv.text = "Counter: $count"

            it?.setBackgroundColor(getColor(R.color.colorBtnIncrement))
        })

        btnDecrement.setOnClickListener {
            count--

            tv.text = "Counter: $count"

            it?.setBackgroundColor(getColor(R.color.colorBtnDecrement))
        }


    }
}
