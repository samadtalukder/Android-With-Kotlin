package com.samad_talukder.androidserviceexample

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.samad_talukder.androidserviceexample.MainActivity.Companion.NOTIFICATION
import com.samad_talukder.androidserviceexample.MainActivity.Companion.STATE
import java.util.concurrent.TimeUnit

class BackgroundService : IntentService("BackgroundService") {

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        Log.e(MainActivity.TAG, "onHandleIntent run on ${Thread.currentThread().name}")

        sendBroadcast(Intent(NOTIFICATION).also {
            it.putExtra(STATE, "START")
        })

        Thread.sleep(TimeUnit.SECONDS.toMillis(2))

        sendBroadcast(Intent(NOTIFICATION).also {
            it.putExtra(STATE, "END")
        })
    }

}