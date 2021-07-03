package com.samad_talukder.androidfragmentcommunication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samad_talukder.androidfragmentcommunication.callbacks.FragmentOnClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FragmentOnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val supportFragment = supportFragmentManager
        val transaction = supportFragment.beginTransaction()

        transaction.replace(R.id.fragmentContainer, SendFragment(), "SendFragment").commit()

    }

    @SuppressLint("SetTextI18n")
    override fun fragmentOnClick(string: String) {

        if (string.isNotEmpty()){
            tvShowMessage.text = "Full Name: $string"
        }


    }


}