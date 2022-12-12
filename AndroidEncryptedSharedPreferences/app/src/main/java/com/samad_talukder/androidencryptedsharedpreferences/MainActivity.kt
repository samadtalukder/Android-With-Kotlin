package com.samad_talukder.androidencryptedsharedpreferences

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var encryptSharedPref: EncryptSharedPref? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        encryptSharedPref = EncryptSharedPref(this)

        encryptSharedPref?.saveString("PREFS_DATA", "122233dkaslfmasdf")

        val user = Users("Samad Talukder", "sam@mail.com")

        encryptSharedPref?.saveObject("PREFS_USER", user)

        Log.e("###", "${encryptSharedPref?.getString("PREFS_DATA")}")
        Log.e("###", "${encryptSharedPref?.getToken()}")
        Log.e("###", "${encryptSharedPref?.getSaveObject("PREFS_USER", Users::class.java)}")

    }
}