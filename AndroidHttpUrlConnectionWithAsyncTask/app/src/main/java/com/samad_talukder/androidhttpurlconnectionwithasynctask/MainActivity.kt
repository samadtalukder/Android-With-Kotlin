package com.samad_talukder.androidhttpurlconnectionwithasynctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CallBtn.setOnClickListener {

            val url =
                "http://api.ipapi.com/103.209.199.247?access_key=56bdd5ee1b3c0cadf213e96e6a98c34a&format=1"

            HttpUrlAsyncTask(this).execute(url)

        }
    }
}