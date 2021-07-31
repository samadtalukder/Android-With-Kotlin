package com.samad_talukder.monitorinternetconnectioninandroidusinglivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkStatusHelper(this@MainActivity).observe(this,{
            when(it){
                NetworkStatus.Available -> Log.e("NET","Connected")
                NetworkStatus.UnAvailable -> Log.e("NET","No Internet")
            }
        })
    }

    private fun display(message: String): String {
        return Toast.makeText(applicationContext, message,Toast.LENGTH_SHORT).show().toString()
    }
}